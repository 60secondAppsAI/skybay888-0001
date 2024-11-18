package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.Flight;





public interface FlightDAO extends GenericDAO<Flight, Integer> {
  
	List<Flight> findAll();
	






}


