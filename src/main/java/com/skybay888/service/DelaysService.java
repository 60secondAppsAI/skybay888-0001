package com.skybay888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay888.domain.Delays;
import com.skybay888.dto.DelaysDTO;
import com.skybay888.dto.DelaysSearchDTO;
import com.skybay888.dto.DelaysPageDTO;
import com.skybay888.dto.DelaysConvertCriteriaDTO;
import com.skybay888.service.GenericService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DelaysService extends GenericService<Delays, Integer> {

	List<Delays> findAll();

	ResultDTO addDelays(DelaysDTO delaysDTO, RequestDTO requestDTO);

	ResultDTO updateDelays(DelaysDTO delaysDTO, RequestDTO requestDTO);

    Page<Delays> getAllDelayss(Pageable pageable);

    Page<Delays> getAllDelayss(Specification<Delays> spec, Pageable pageable);

	ResponseEntity<DelaysPageDTO> getDelayss(DelaysSearchDTO delaysSearchDTO);
	
	List<DelaysDTO> convertDelayssToDelaysDTOs(List<Delays> delayss, DelaysConvertCriteriaDTO convertCriteria);

	DelaysDTO getDelaysDTOById(Integer delaysId);







}





