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

import com.skybay888.domain.CustomerServiceRequest;
import com.skybay888.dto.CustomerServiceRequestDTO;
import com.skybay888.dto.CustomerServiceRequestSearchDTO;
import com.skybay888.dto.CustomerServiceRequestPageDTO;
import com.skybay888.service.CustomerServiceRequestService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/customerServiceRequest")
@RestController
public class CustomerServiceRequestController {

	private final static Logger logger = LoggerFactory.getLogger(CustomerServiceRequestController.class);

	@Autowired
	CustomerServiceRequestService customerServiceRequestService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CustomerServiceRequest> getAll() {

		List<CustomerServiceRequest> customerServiceRequests = customerServiceRequestService.findAll();
		
		return customerServiceRequests;	
	}

	@GetMapping(value = "/{customerServiceRequestId}")
	@ResponseBody
	public CustomerServiceRequestDTO getCustomerServiceRequest(@PathVariable Integer customerServiceRequestId) {
		
		return (customerServiceRequestService.getCustomerServiceRequestDTOById(customerServiceRequestId));
	}

 	@RequestMapping(value = "/addCustomerServiceRequest", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomerServiceRequest(@RequestBody CustomerServiceRequestDTO customerServiceRequestDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = customerServiceRequestService.addCustomerServiceRequest(customerServiceRequestDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/customerServiceRequests")
	public ResponseEntity<CustomerServiceRequestPageDTO> getCustomerServiceRequests(CustomerServiceRequestSearchDTO customerServiceRequestSearchDTO) {
 
		return customerServiceRequestService.getCustomerServiceRequests(customerServiceRequestSearchDTO);
	}	

	@RequestMapping(value = "/updateCustomerServiceRequest", method = RequestMethod.POST)
	public ResponseEntity<?> updateCustomerServiceRequest(@RequestBody CustomerServiceRequestDTO customerServiceRequestDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = customerServiceRequestService.updateCustomerServiceRequest(customerServiceRequestDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
