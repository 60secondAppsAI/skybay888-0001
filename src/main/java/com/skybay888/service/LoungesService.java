package com.skybay888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay888.domain.Lounges;
import com.skybay888.dto.LoungesDTO;
import com.skybay888.dto.LoungesSearchDTO;
import com.skybay888.dto.LoungesPageDTO;
import com.skybay888.dto.LoungesConvertCriteriaDTO;
import com.skybay888.service.GenericService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LoungesService extends GenericService<Lounges, Integer> {

	List<Lounges> findAll();

	ResultDTO addLounges(LoungesDTO loungesDTO, RequestDTO requestDTO);

	ResultDTO updateLounges(LoungesDTO loungesDTO, RequestDTO requestDTO);

    Page<Lounges> getAllLoungess(Pageable pageable);

    Page<Lounges> getAllLoungess(Specification<Lounges> spec, Pageable pageable);

	ResponseEntity<LoungesPageDTO> getLoungess(LoungesSearchDTO loungesSearchDTO);
	
	List<LoungesDTO> convertLoungessToLoungesDTOs(List<Lounges> loungess, LoungesConvertCriteriaDTO convertCriteria);

	LoungesDTO getLoungesDTOById(Integer loungesId);







}





