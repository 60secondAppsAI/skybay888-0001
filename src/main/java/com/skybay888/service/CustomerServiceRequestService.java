package com.skybay888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay888.domain.CustomerServiceRequest;
import com.skybay888.dto.CustomerServiceRequestDTO;
import com.skybay888.dto.CustomerServiceRequestSearchDTO;
import com.skybay888.dto.CustomerServiceRequestPageDTO;
import com.skybay888.dto.CustomerServiceRequestConvertCriteriaDTO;
import com.skybay888.service.GenericService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CustomerServiceRequestService extends GenericService<CustomerServiceRequest, Integer> {

	List<CustomerServiceRequest> findAll();

	ResultDTO addCustomerServiceRequest(CustomerServiceRequestDTO customerServiceRequestDTO, RequestDTO requestDTO);

	ResultDTO updateCustomerServiceRequest(CustomerServiceRequestDTO customerServiceRequestDTO, RequestDTO requestDTO);

    Page<CustomerServiceRequest> getAllCustomerServiceRequests(Pageable pageable);

    Page<CustomerServiceRequest> getAllCustomerServiceRequests(Specification<CustomerServiceRequest> spec, Pageable pageable);

	ResponseEntity<CustomerServiceRequestPageDTO> getCustomerServiceRequests(CustomerServiceRequestSearchDTO customerServiceRequestSearchDTO);
	
	List<CustomerServiceRequestDTO> convertCustomerServiceRequestsToCustomerServiceRequestDTOs(List<CustomerServiceRequest> customerServiceRequests, CustomerServiceRequestConvertCriteriaDTO convertCriteria);

	CustomerServiceRequestDTO getCustomerServiceRequestDTOById(Integer customerServiceRequestId);







}





