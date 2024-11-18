package com.skybay888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay888.domain.FlightStatus;
import com.skybay888.dto.FlightStatusDTO;
import com.skybay888.dto.FlightStatusSearchDTO;
import com.skybay888.dto.FlightStatusPageDTO;
import com.skybay888.dto.FlightStatusConvertCriteriaDTO;
import com.skybay888.service.GenericService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FlightStatusService extends GenericService<FlightStatus, Integer> {

	List<FlightStatus> findAll();

	ResultDTO addFlightStatus(FlightStatusDTO flightStatusDTO, RequestDTO requestDTO);

	ResultDTO updateFlightStatus(FlightStatusDTO flightStatusDTO, RequestDTO requestDTO);

    Page<FlightStatus> getAllFlightStatuss(Pageable pageable);

    Page<FlightStatus> getAllFlightStatuss(Specification<FlightStatus> spec, Pageable pageable);

	ResponseEntity<FlightStatusPageDTO> getFlightStatuss(FlightStatusSearchDTO flightStatusSearchDTO);
	
	List<FlightStatusDTO> convertFlightStatussToFlightStatusDTOs(List<FlightStatus> flightStatuss, FlightStatusConvertCriteriaDTO convertCriteria);

	FlightStatusDTO getFlightStatusDTOById(Integer flightStatusId);







}





