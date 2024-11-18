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

import com.skybay888.domain.Delays;
import com.skybay888.dto.DelaysDTO;
import com.skybay888.dto.DelaysSearchDTO;
import com.skybay888.dto.DelaysPageDTO;
import com.skybay888.service.DelaysService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/delays")
@RestController
public class DelaysController {

	private final static Logger logger = LoggerFactory.getLogger(DelaysController.class);

	@Autowired
	DelaysService delaysService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Delays> getAll() {

		List<Delays> delayss = delaysService.findAll();
		
		return delayss;	
	}

	@GetMapping(value = "/{delaysId}")
	@ResponseBody
	public DelaysDTO getDelays(@PathVariable Integer delaysId) {
		
		return (delaysService.getDelaysDTOById(delaysId));
	}

 	@RequestMapping(value = "/addDelays", method = RequestMethod.POST)
	public ResponseEntity<?> addDelays(@RequestBody DelaysDTO delaysDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = delaysService.addDelays(delaysDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/delayss")
	public ResponseEntity<DelaysPageDTO> getDelayss(DelaysSearchDTO delaysSearchDTO) {
 
		return delaysService.getDelayss(delaysSearchDTO);
	}	

	@RequestMapping(value = "/updateDelays", method = RequestMethod.POST)
	public ResponseEntity<?> updateDelays(@RequestBody DelaysDTO delaysDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = delaysService.updateDelays(delaysDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
