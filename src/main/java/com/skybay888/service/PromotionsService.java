package com.skybay888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay888.domain.Promotions;
import com.skybay888.dto.PromotionsDTO;
import com.skybay888.dto.PromotionsSearchDTO;
import com.skybay888.dto.PromotionsPageDTO;
import com.skybay888.dto.PromotionsConvertCriteriaDTO;
import com.skybay888.service.GenericService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PromotionsService extends GenericService<Promotions, Integer> {

	List<Promotions> findAll();

	ResultDTO addPromotions(PromotionsDTO promotionsDTO, RequestDTO requestDTO);

	ResultDTO updatePromotions(PromotionsDTO promotionsDTO, RequestDTO requestDTO);

    Page<Promotions> getAllPromotionss(Pageable pageable);

    Page<Promotions> getAllPromotionss(Specification<Promotions> spec, Pageable pageable);

	ResponseEntity<PromotionsPageDTO> getPromotionss(PromotionsSearchDTO promotionsSearchDTO);
	
	List<PromotionsDTO> convertPromotionssToPromotionsDTOs(List<Promotions> promotionss, PromotionsConvertCriteriaDTO convertCriteria);

	PromotionsDTO getPromotionsDTOById(Integer promotionsId);







}





