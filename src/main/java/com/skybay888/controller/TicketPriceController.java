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

import com.skybay888.domain.TicketPrice;
import com.skybay888.dto.TicketPriceDTO;
import com.skybay888.dto.TicketPriceSearchDTO;
import com.skybay888.dto.TicketPricePageDTO;
import com.skybay888.service.TicketPriceService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/ticketPrice")
@RestController
public class TicketPriceController {

	private final static Logger logger = LoggerFactory.getLogger(TicketPriceController.class);

	@Autowired
	TicketPriceService ticketPriceService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<TicketPrice> getAll() {

		List<TicketPrice> ticketPrices = ticketPriceService.findAll();
		
		return ticketPrices;	
	}

	@GetMapping(value = "/{ticketPriceId}")
	@ResponseBody
	public TicketPriceDTO getTicketPrice(@PathVariable Integer ticketPriceId) {
		
		return (ticketPriceService.getTicketPriceDTOById(ticketPriceId));
	}

 	@RequestMapping(value = "/addTicketPrice", method = RequestMethod.POST)
	public ResponseEntity<?> addTicketPrice(@RequestBody TicketPriceDTO ticketPriceDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = ticketPriceService.addTicketPrice(ticketPriceDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/ticketPrices")
	public ResponseEntity<TicketPricePageDTO> getTicketPrices(TicketPriceSearchDTO ticketPriceSearchDTO) {
 
		return ticketPriceService.getTicketPrices(ticketPriceSearchDTO);
	}	

	@RequestMapping(value = "/updateTicketPrice", method = RequestMethod.POST)
	public ResponseEntity<?> updateTicketPrice(@RequestBody TicketPriceDTO ticketPriceDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = ticketPriceService.updateTicketPrice(ticketPriceDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
