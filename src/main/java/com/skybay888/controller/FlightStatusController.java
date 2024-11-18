package com.skybay888.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skybay888.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skybay888.domain.FlightStatus;
import com.skybay888.dto.FlightStatusDTO;
import com.skybay888.dto.FlightStatusSearchDTO;
import com.skybay888.dto.FlightStatusPageDTO;
import com.skybay888.service.FlightStatusService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/flightStatus")
@RestController
public class FlightStatusController {

	private final static Logger logger = LoggerFactory.getLogger(FlightStatusController.class);

	@Autowired
	FlightStatusService flightStatusService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<FlightStatus> getAll() {

		List<FlightStatus> flightStatuss = flightStatusService.findAll();
		
		return flightStatuss;	
	}

	@GetMapping(value = "/{flightStatusId}")
	@ResponseBody
	public FlightStatusDTO getFlightStatus(@PathVariable Integer flightStatusId) {
		
		return (flightStatusService.getFlightStatusDTOById(flightStatusId));
	}

 	@RequestMapping(value = "/addFlightStatus", method = RequestMethod.POST)
	public ResponseEntity<?> addFlightStatus(@RequestBody FlightStatusDTO flightStatusDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flightStatusService.addFlightStatus(flightStatusDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/flightStatuss")
	public ResponseEntity<FlightStatusPageDTO> getFlightStatuss(FlightStatusSearchDTO flightStatusSearchDTO) {
 
		return flightStatusService.getFlightStatuss(flightStatusSearchDTO);
	}	

	@RequestMapping(value = "/updateFlightStatus", method = RequestMethod.POST)
	public ResponseEntity<?> updateFlightStatus(@RequestBody FlightStatusDTO flightStatusDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = flightStatusService.updateFlightStatus(flightStatusDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
