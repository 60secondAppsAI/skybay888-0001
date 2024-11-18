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
import com.skybay888.dao.PromotionsDAO;
import com.skybay888.domain.Promotions;
import com.skybay888.dto.PromotionsDTO;
import com.skybay888.dto.PromotionsSearchDTO;
import com.skybay888.dto.PromotionsPageDTO;
import com.skybay888.dto.PromotionsConvertCriteriaDTO;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import com.skybay888.service.PromotionsService;
import com.skybay888.util.ControllerUtils;





@Service
public class PromotionsServiceImpl extends GenericServiceImpl<Promotions, Integer> implements PromotionsService {

    private final static Logger logger = LoggerFactory.getLogger(PromotionsServiceImpl.class);

	@Autowired
	PromotionsDAO promotionsDao;

	


	@Override
	public GenericDAO<Promotions, Integer> getDAO() {
		return (GenericDAO<Promotions, Integer>) promotionsDao;
	}
	
	public List<Promotions> findAll () {
		List<Promotions> promotionss = promotionsDao.findAll();
		
		return promotionss;	
		
	}

	public ResultDTO addPromotions(PromotionsDTO promotionsDTO, RequestDTO requestDTO) {

		Promotions promotions = new Promotions();

		promotions.setPromotionsId(promotionsDTO.getPromotionsId());


		promotions.setPromotionName(promotionsDTO.getPromotionName());


		promotions.setDescription(promotionsDTO.getDescription());


		promotions.setValidity(promotionsDTO.getValidity());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		promotions = promotionsDao.save(promotions);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Promotions> getAllPromotionss(Pageable pageable) {
		return promotionsDao.findAll(pageable);
	}

	public Page<Promotions> getAllPromotionss(Specification<Promotions> spec, Pageable pageable) {
		return promotionsDao.findAll(spec, pageable);
	}

	public ResponseEntity<PromotionsPageDTO> getPromotionss(PromotionsSearchDTO promotionsSearchDTO) {
	
			Integer promotionsId = promotionsSearchDTO.getPromotionsId(); 
 			String promotionName = promotionsSearchDTO.getPromotionName(); 
 			String description = promotionsSearchDTO.getDescription(); 
   			String sortBy = promotionsSearchDTO.getSortBy();
			String sortOrder = promotionsSearchDTO.getSortOrder();
			String searchQuery = promotionsSearchDTO.getSearchQuery();
			Integer page = promotionsSearchDTO.getPage();
			Integer size = promotionsSearchDTO.getSize();

	        Specification<Promotions> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, promotionsId, "promotionsId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, promotionName, "promotionName"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("promotionName")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Promotions> promotionss = this.getAllPromotionss(spec, pageable);
		
		//System.out.println(String.valueOf(promotionss.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(promotionss.getTotalPages()));
		
		List<Promotions> promotionssList = promotionss.getContent();
		
		PromotionsConvertCriteriaDTO convertCriteria = new PromotionsConvertCriteriaDTO();
		List<PromotionsDTO> promotionsDTOs = this.convertPromotionssToPromotionsDTOs(promotionssList,convertCriteria);
		
		PromotionsPageDTO promotionsPageDTO = new PromotionsPageDTO();
		promotionsPageDTO.setPromotionss(promotionsDTOs);
		promotionsPageDTO.setTotalElements(promotionss.getTotalElements());
		return ResponseEntity.ok(promotionsPageDTO);
	}

	public List<PromotionsDTO> convertPromotionssToPromotionsDTOs(List<Promotions> promotionss, PromotionsConvertCriteriaDTO convertCriteria) {
		
		List<PromotionsDTO> promotionsDTOs = new ArrayList<PromotionsDTO>();
		
		for (Promotions promotions : promotionss) {
			promotionsDTOs.add(convertPromotionsToPromotionsDTO(promotions,convertCriteria));
		}
		
		return promotionsDTOs;

	}
	
	public PromotionsDTO convertPromotionsToPromotionsDTO(Promotions promotions, PromotionsConvertCriteriaDTO convertCriteria) {
		
		PromotionsDTO promotionsDTO = new PromotionsDTO();
		
		promotionsDTO.setPromotionsId(promotions.getPromotionsId());

	
		promotionsDTO.setPromotionName(promotions.getPromotionName());

	
		promotionsDTO.setDescription(promotions.getDescription());

	
		promotionsDTO.setValidity(promotions.getValidity());

	

		
		return promotionsDTO;
	}

	public ResultDTO updatePromotions(PromotionsDTO promotionsDTO, RequestDTO requestDTO) {
		
		Promotions promotions = promotionsDao.getById(promotionsDTO.getPromotionsId());

		promotions.setPromotionsId(ControllerUtils.setValue(promotions.getPromotionsId(), promotionsDTO.getPromotionsId()));

		promotions.setPromotionName(ControllerUtils.setValue(promotions.getPromotionName(), promotionsDTO.getPromotionName()));

		promotions.setDescription(ControllerUtils.setValue(promotions.getDescription(), promotionsDTO.getDescription()));

		promotions.setValidity(ControllerUtils.setValue(promotions.getValidity(), promotionsDTO.getValidity()));



        promotions = promotionsDao.save(promotions);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PromotionsDTO getPromotionsDTOById(Integer promotionsId) {
	
		Promotions promotions = promotionsDao.getById(promotionsId);
			
		
		PromotionsConvertCriteriaDTO convertCriteria = new PromotionsConvertCriteriaDTO();
		return(this.convertPromotionsToPromotionsDTO(promotions,convertCriteria));
	}







}
