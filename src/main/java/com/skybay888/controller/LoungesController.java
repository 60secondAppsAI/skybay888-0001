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

import com.skybay888.domain.Lounges;
import com.skybay888.dto.LoungesDTO;
import com.skybay888.dto.LoungesSearchDTO;
import com.skybay888.dto.LoungesPageDTO;
import com.skybay888.service.LoungesService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/lounges")
@RestController
public class LoungesController {

	private final static Logger logger = LoggerFactory.getLogger(LoungesController.class);

	@Autowired
	LoungesService loungesService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Lounges> getAll() {

		List<Lounges> loungess = loungesService.findAll();
		
		return loungess;	
	}

	@GetMapping(value = "/{loungesId}")
	@ResponseBody
	public LoungesDTO getLounges(@PathVariable Integer loungesId) {
		
		return (loungesService.getLoungesDTOById(loungesId));
	}

 	@RequestMapping(value = "/addLounges", method = RequestMethod.POST)
	public ResponseEntity<?> addLounges(@RequestBody LoungesDTO loungesDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = loungesService.addLounges(loungesDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/loungess")
	public ResponseEntity<LoungesPageDTO> getLoungess(LoungesSearchDTO loungesSearchDTO) {
 
		return loungesService.getLoungess(loungesSearchDTO);
	}	

	@RequestMapping(value = "/updateLounges", method = RequestMethod.POST)
	public ResponseEntity<?> updateLounges(@RequestBody LoungesDTO loungesDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = loungesService.updateLounges(loungesDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
