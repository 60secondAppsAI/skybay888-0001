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
import com.skybay888.dao.LoungesDAO;
import com.skybay888.domain.Lounges;
import com.skybay888.dto.LoungesDTO;
import com.skybay888.dto.LoungesSearchDTO;
import com.skybay888.dto.LoungesPageDTO;
import com.skybay888.dto.LoungesConvertCriteriaDTO;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import com.skybay888.service.LoungesService;
import com.skybay888.util.ControllerUtils;





@Service
public class LoungesServiceImpl extends GenericServiceImpl<Lounges, Integer> implements LoungesService {

    private final static Logger logger = LoggerFactory.getLogger(LoungesServiceImpl.class);

	@Autowired
	LoungesDAO loungesDao;

	


	@Override
	public GenericDAO<Lounges, Integer> getDAO() {
		return (GenericDAO<Lounges, Integer>) loungesDao;
	}
	
	public List<Lounges> findAll () {
		List<Lounges> loungess = loungesDao.findAll();
		
		return loungess;	
		
	}

	public ResultDTO addLounges(LoungesDTO loungesDTO, RequestDTO requestDTO) {

		Lounges lounges = new Lounges();

		lounges.setLoungesId(loungesDTO.getLoungesId());


		lounges.setLoungeName(loungesDTO.getLoungeName());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		lounges = loungesDao.save(lounges);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Lounges> getAllLoungess(Pageable pageable) {
		return loungesDao.findAll(pageable);
	}

	public Page<Lounges> getAllLoungess(Specification<Lounges> spec, Pageable pageable) {
		return loungesDao.findAll(spec, pageable);
	}

	public ResponseEntity<LoungesPageDTO> getLoungess(LoungesSearchDTO loungesSearchDTO) {
	
			Integer loungesId = loungesSearchDTO.getLoungesId(); 
 			String loungeName = loungesSearchDTO.getLoungeName(); 
 			String sortBy = loungesSearchDTO.getSortBy();
			String sortOrder = loungesSearchDTO.getSortOrder();
			String searchQuery = loungesSearchDTO.getSearchQuery();
			Integer page = loungesSearchDTO.getPage();
			Integer size = loungesSearchDTO.getSize();

	        Specification<Lounges> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, loungesId, "loungesId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, loungeName, "loungeName"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("loungeName")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Lounges> loungess = this.getAllLoungess(spec, pageable);
		
		//System.out.println(String.valueOf(loungess.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(loungess.getTotalPages()));
		
		List<Lounges> loungessList = loungess.getContent();
		
		LoungesConvertCriteriaDTO convertCriteria = new LoungesConvertCriteriaDTO();
		List<LoungesDTO> loungesDTOs = this.convertLoungessToLoungesDTOs(loungessList,convertCriteria);
		
		LoungesPageDTO loungesPageDTO = new LoungesPageDTO();
		loungesPageDTO.setLoungess(loungesDTOs);
		loungesPageDTO.setTotalElements(loungess.getTotalElements());
		return ResponseEntity.ok(loungesPageDTO);
	}

	public List<LoungesDTO> convertLoungessToLoungesDTOs(List<Lounges> loungess, LoungesConvertCriteriaDTO convertCriteria) {
		
		List<LoungesDTO> loungesDTOs = new ArrayList<LoungesDTO>();
		
		for (Lounges lounges : loungess) {
			loungesDTOs.add(convertLoungesToLoungesDTO(lounges,convertCriteria));
		}
		
		return loungesDTOs;

	}
	
	public LoungesDTO convertLoungesToLoungesDTO(Lounges lounges, LoungesConvertCriteriaDTO convertCriteria) {
		
		LoungesDTO loungesDTO = new LoungesDTO();
		
		loungesDTO.setLoungesId(lounges.getLoungesId());

	
		loungesDTO.setLoungeName(lounges.getLoungeName());

	

		
		return loungesDTO;
	}

	public ResultDTO updateLounges(LoungesDTO loungesDTO, RequestDTO requestDTO) {
		
		Lounges lounges = loungesDao.getById(loungesDTO.getLoungesId());

		lounges.setLoungesId(ControllerUtils.setValue(lounges.getLoungesId(), loungesDTO.getLoungesId()));

		lounges.setLoungeName(ControllerUtils.setValue(lounges.getLoungeName(), loungesDTO.getLoungeName()));



        lounges = loungesDao.save(lounges);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public LoungesDTO getLoungesDTOById(Integer loungesId) {
	
		Lounges lounges = loungesDao.getById(loungesId);
			
		
		LoungesConvertCriteriaDTO convertCriteria = new LoungesConvertCriteriaDTO();
		return(this.convertLoungesToLoungesDTO(lounges,convertCriteria));
	}







}
