package com.skybay888.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skybay888.dao.GenericDAO;
import com.skybay888.service.GenericService;
import com.skybay888.service.impl.GenericServiceImpl;
import com.skybay888.dao.CustomerServiceRequestDAO;
import com.skybay888.domain.CustomerServiceRequest;
import com.skybay888.dto.CustomerServiceRequestDTO;
import com.skybay888.dto.CustomerServiceRequestSearchDTO;
import com.skybay888.dto.CustomerServiceRequestPageDTO;
import com.skybay888.dto.CustomerServiceRequestConvertCriteriaDTO;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import com.skybay888.service.CustomerServiceRequestService;
import com.skybay888.util.ControllerUtils;





@Service
public class CustomerServiceRequestServiceImpl extends GenericServiceImpl<CustomerServiceRequest, Integer> implements CustomerServiceRequestService {

    private final static Logger logger = LoggerFactory.getLogger(CustomerServiceRequestServiceImpl.class);

	@Autowired
	CustomerServiceRequestDAO customerServiceRequestDao;

	


	@Override
	public GenericDAO<CustomerServiceRequest, Integer> getDAO() {
		return (GenericDAO<CustomerServiceRequest, Integer>) customerServiceRequestDao;
	}
	
	public List<CustomerServiceRequest> findAll () {
		List<CustomerServiceRequest> customerServiceRequests = customerServiceRequestDao.findAll();
		
		return customerServiceRequests;	
		
	}

	public ResultDTO addCustomerServiceRequest(CustomerServiceRequestDTO customerServiceRequestDTO, RequestDTO requestDTO) {

		CustomerServiceRequest customerServiceRequest = new CustomerServiceRequest();

		customerServiceRequest.setCustomerServiceRequestId(customerServiceRequestDTO.getCustomerServiceRequestId());


		customerServiceRequest.setRequestType(customerServiceRequestDTO.getRequestType());


		customerServiceRequest.setRequestDate(customerServiceRequestDTO.getRequestDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		customerServiceRequest = customerServiceRequestDao.save(customerServiceRequest);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CustomerServiceRequest> getAllCustomerServiceRequests(Pageable pageable) {
		return customerServiceRequestDao.findAll(pageable);
	}

	public Page<CustomerServiceRequest> getAllCustomerServiceRequests(Specification<CustomerServiceRequest> spec, Pageable pageable) {
		return customerServiceRequestDao.findAll(spec, pageable);
	}

	public ResponseEntity<CustomerServiceRequestPageDTO> getCustomerServiceRequests(CustomerServiceRequestSearchDTO customerServiceRequestSearchDTO) {
	
			Integer customerServiceRequestId = customerServiceRequestSearchDTO.getCustomerServiceRequestId(); 
 			String requestType = customerServiceRequestSearchDTO.getRequestType(); 
   			String sortBy = customerServiceRequestSearchDTO.getSortBy();
			String sortOrder = customerServiceRequestSearchDTO.getSortOrder();
			String searchQuery = customerServiceRequestSearchDTO.getSearchQuery();
			Integer page = customerServiceRequestSearchDTO.getPage();
			Integer size = customerServiceRequestSearchDTO.getSize();

	        Specification<CustomerServiceRequest> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, customerServiceRequestId, "customerServiceRequestId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, requestType, "requestType"); 
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("requestType")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<CustomerServiceRequest> customerServiceRequests = this.getAllCustomerServiceRequests(spec, pageable);
		
		//System.out.println(String.valueOf(customerServiceRequests.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(customerServiceRequests.getTotalPages()));
		
		List<CustomerServiceRequest> customerServiceRequestsList = customerServiceRequests.getContent();
		
		CustomerServiceRequestConvertCriteriaDTO convertCriteria = new CustomerServiceRequestConvertCriteriaDTO();
		List<CustomerServiceRequestDTO> customerServiceRequestDTOs = this.convertCustomerServiceRequestsToCustomerServiceRequestDTOs(customerServiceRequestsList,convertCriteria);
		
		CustomerServiceRequestPageDTO customerServiceRequestPageDTO = new CustomerServiceRequestPageDTO();
		customerServiceRequestPageDTO.setCustomerServiceRequests(customerServiceRequestDTOs);
		customerServiceRequestPageDTO.setTotalElements(customerServiceRequests.getTotalElements());
		return ResponseEntity.ok(customerServiceRequestPageDTO);
	}

	public List<CustomerServiceRequestDTO> convertCustomerServiceRequestsToCustomerServiceRequestDTOs(List<CustomerServiceRequest> customerServiceRequests, CustomerServiceRequestConvertCriteriaDTO convertCriteria) {
		
		List<CustomerServiceRequestDTO> customerServiceRequestDTOs = new ArrayList<CustomerServiceRequestDTO>();
		
		for (CustomerServiceRequest customerServiceRequest : customerServiceRequests) {
			customerServiceRequestDTOs.add(convertCustomerServiceRequestToCustomerServiceRequestDTO(customerServiceRequest,convertCriteria));
		}
		
		return customerServiceRequestDTOs;

	}
	
	public CustomerServiceRequestDTO convertCustomerServiceRequestToCustomerServiceRequestDTO(CustomerServiceRequest customerServiceRequest, CustomerServiceRequestConvertCriteriaDTO convertCriteria) {
		
		CustomerServiceRequestDTO customerServiceRequestDTO = new CustomerServiceRequestDTO();
		
		customerServiceRequestDTO.setCustomerServiceRequestId(customerServiceRequest.getCustomerServiceRequestId());

	
		customerServiceRequestDTO.setRequestType(customerServiceRequest.getRequestType());

	
		customerServiceRequestDTO.setRequestDate(customerServiceRequest.getRequestDate());

	

		
		return customerServiceRequestDTO;
	}

	public ResultDTO updateCustomerServiceRequest(CustomerServiceRequestDTO customerServiceRequestDTO, RequestDTO requestDTO) {
		
		CustomerServiceRequest customerServiceRequest = customerServiceRequestDao.getById(customerServiceRequestDTO.getCustomerServiceRequestId());

		customerServiceRequest.setCustomerServiceRequestId(ControllerUtils.setValue(customerServiceRequest.getCustomerServiceRequestId(), customerServiceRequestDTO.getCustomerServiceRequestId()));

		customerServiceRequest.setRequestType(ControllerUtils.setValue(customerServiceRequest.getRequestType(), customerServiceRequestDTO.getRequestType()));

		customerServiceRequest.setRequestDate(ControllerUtils.setValue(customerServiceRequest.getRequestDate(), customerServiceRequestDTO.getRequestDate()));



        customerServiceRequest = customerServiceRequestDao.save(customerServiceRequest);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CustomerServiceRequestDTO getCustomerServiceRequestDTOById(Integer customerServiceRequestId) {
	
		CustomerServiceRequest customerServiceRequest = customerServiceRequestDao.getById(customerServiceRequestId);
			
		
		CustomerServiceRequestConvertCriteriaDTO convertCriteria = new CustomerServiceRequestConvertCriteriaDTO();
		return(this.convertCustomerServiceRequestToCustomerServiceRequestDTO(customerServiceRequest,convertCriteria));
	}







}
