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

import com.skybay888.domain.Promotions;
import com.skybay888.dto.PromotionsDTO;
import com.skybay888.dto.PromotionsSearchDTO;
import com.skybay888.dto.PromotionsPageDTO;
import com.skybay888.service.PromotionsService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/promotions")
@RestController
public class PromotionsController {

	private final static Logger logger = LoggerFactory.getLogger(PromotionsController.class);

	@Autowired
	PromotionsService promotionsService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Promotions> getAll() {

		List<Promotions> promotionss = promotionsService.findAll();
		
		return promotionss;	
	}

	@GetMapping(value = "/{promotionsId}")
	@ResponseBody
	public PromotionsDTO getPromotions(@PathVariable Integer promotionsId) {
		
		return (promotionsService.getPromotionsDTOById(promotionsId));
	}

 	@RequestMapping(value = "/addPromotions", method = RequestMethod.POST)
	public ResponseEntity<?> addPromotions(@RequestBody PromotionsDTO promotionsDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = promotionsService.addPromotions(promotionsDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/promotionss")
	public ResponseEntity<PromotionsPageDTO> getPromotionss(PromotionsSearchDTO promotionsSearchDTO) {
 
		return promotionsService.getPromotionss(promotionsSearchDTO);
	}	

	@RequestMapping(value = "/updatePromotions", method = RequestMethod.POST)
	public ResponseEntity<?> updatePromotions(@RequestBody PromotionsDTO promotionsDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = promotionsService.updatePromotions(promotionsDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
