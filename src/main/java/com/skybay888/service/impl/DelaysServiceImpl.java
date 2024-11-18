package com.skybay888.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skybay888.dao.GenericDAO;
import com.skybay888.service.GenericService;
import com.skybay888.service.impl.GenericServiceImpl;
import com.skybay888.dao.DelaysDAO;
import com.skybay888.domain.Delays;
import com.skybay888.dto.DelaysDTO;
import com.skybay888.dto.DelaysSearchDTO;
import com.skybay888.dto.DelaysPageDTO;
import com.skybay888.dto.DelaysConvertCriteriaDTO;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import com.skybay888.service.DelaysService;
import com.skybay888.util.ControllerUtils;





@Service
public class DelaysServiceImpl extends GenericServiceImpl<Delays, Integer> implements DelaysService {

    private final static Logger logger = LoggerFactory.getLogger(DelaysServiceImpl.class);

	@Autowired
	DelaysDAO delaysDao;

	


	@Override
	public GenericDAO<Delays, Integer> getDAO() {
		return (GenericDAO<Delays, Integer>) delaysDao;
	}
	
	public List<Delays> findAll () {
		List<Delays> delayss = delaysDao.findAll();
		
		return delayss;	
		
	}

	public ResultDTO addDelays(DelaysDTO delaysDTO, RequestDTO requestDTO) {

		Delays delays = new Delays();

		delays.setDelaysId(delaysDTO.getDelaysId());


		delays.setReason(delaysDTO.getReason());


		delays.setDuration(delaysDTO.getDuration());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		delays = delaysDao.save(delays);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Delays> getAllDelayss(Pageable pageable) {
		return delaysDao.findAll(pageable);
	}

	public Page<Delays> getAllDelayss(Specification<Delays> spec, Pageable pageable) {
		return delaysDao.findAll(spec, pageable);
	}

	public ResponseEntity<DelaysPageDTO> getDelayss(DelaysSearchDTO delaysSearchDTO) {
	
			Integer delaysId = delaysSearchDTO.getDelaysId(); 
 			String reason = delaysSearchDTO.getReason(); 
  			String sortBy = delaysSearchDTO.getSortBy();
			String sortOrder = delaysSearchDTO.getSortOrder();
			String searchQuery = delaysSearchDTO.getSearchQuery();
			Integer page = delaysSearchDTO.getPage();
			Integer size = delaysSearchDTO.getSize();

	        Specification<Delays> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, delaysId, "delaysId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, reason, "reason"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("reason")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Delays> delayss = this.getAllDelayss(spec, pageable);
		
		//System.out.println(String.valueOf(delayss.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(delayss.getTotalPages()));
		
		List<Delays> delayssList = delayss.getContent();
		
		DelaysConvertCriteriaDTO convertCriteria = new DelaysConvertCriteriaDTO();
		List<DelaysDTO> delaysDTOs = this.convertDelayssToDelaysDTOs(delayssList,convertCriteria);
		
		DelaysPageDTO delaysPageDTO = new DelaysPageDTO();
		delaysPageDTO.setDelayss(delaysDTOs);
		delaysPageDTO.setTotalElements(delayss.getTotalElements());
		return ResponseEntity.ok(delaysPageDTO);
	}

	public List<DelaysDTO> convertDelayssToDelaysDTOs(List<Delays> delayss, DelaysConvertCriteriaDTO convertCriteria) {
		
		List<DelaysDTO> delaysDTOs = new ArrayList<DelaysDTO>();
		
		for (Delays delays : delayss) {
			delaysDTOs.add(convertDelaysToDelaysDTO(delays,convertCriteria));
		}
		
		return delaysDTOs;

	}
	
	public DelaysDTO convertDelaysToDelaysDTO(Delays delays, DelaysConvertCriteriaDTO convertCriteria) {
		
		DelaysDTO delaysDTO = new DelaysDTO();
		
		delaysDTO.setDelaysId(delays.getDelaysId());

	
		delaysDTO.setReason(delays.getReason());

	
		delaysDTO.setDuration(delays.getDuration());

	

		
		return delaysDTO;
	}

	public ResultDTO updateDelays(DelaysDTO delaysDTO, RequestDTO requestDTO) {
		
		Delays delays = delaysDao.getById(delaysDTO.getDelaysId());

		delays.setDelaysId(ControllerUtils.setValue(delays.getDelaysId(), delaysDTO.getDelaysId()));

		delays.setReason(ControllerUtils.setValue(delays.getReason(), delaysDTO.getReason()));

		delays.setDuration(ControllerUtils.setValue(delays.getDuration(), delaysDTO.getDuration()));



        delays = delaysDao.save(delays);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DelaysDTO getDelaysDTOById(Integer delaysId) {
	
		Delays delays = delaysDao.getById(delaysId);
			
		
		DelaysConvertCriteriaDTO convertCriteria = new DelaysConvertCriteriaDTO();
		return(this.convertDelaysToDelaysDTO(delays,convertCriteria));
	}







}
