package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.Passenger;





public interface PassengerDAO extends GenericDAO<Passenger, Integer> {
  
	List<Passenger> findAll();
	






}


