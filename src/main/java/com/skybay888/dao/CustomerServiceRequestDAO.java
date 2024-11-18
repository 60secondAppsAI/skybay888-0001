package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.CustomerServiceRequest;





public interface CustomerServiceRequestDAO extends GenericDAO<CustomerServiceRequest, Integer> {
  
	List<CustomerServiceRequest> findAll();
	






}


