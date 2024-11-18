package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.FlightStatus;





public interface FlightStatusDAO extends GenericDAO<FlightStatus, Integer> {
  
	List<FlightStatus> findAll();
	






}


