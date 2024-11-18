package com.skybay888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay888.domain.CheckIn;
import com.skybay888.dto.CheckInDTO;
import com.skybay888.dto.CheckInSearchDTO;
import com.skybay888.dto.CheckInPageDTO;
import com.skybay888.dto.CheckInConvertCriteriaDTO;
import com.skybay888.service.GenericService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CheckInService extends GenericService<CheckIn, Integer> {

	List<CheckIn> findAll();

	ResultDTO addCheckIn(CheckInDTO checkInDTO, RequestDTO requestDTO);

	ResultDTO updateCheckIn(CheckInDTO checkInDTO, RequestDTO requestDTO);

    Page<CheckIn> getAllCheckIns(Pageable pageable);

    Page<CheckIn> getAllCheckIns(Specification<CheckIn> spec, Pageable pageable);

	ResponseEntity<CheckInPageDTO> getCheckIns(CheckInSearchDTO checkInSearchDTO);
	
	List<CheckInDTO> convertCheckInsToCheckInDTOs(List<CheckIn> checkIns, CheckInConvertCriteriaDTO convertCriteria);

	CheckInDTO getCheckInDTOById(Integer checkInId);







}




