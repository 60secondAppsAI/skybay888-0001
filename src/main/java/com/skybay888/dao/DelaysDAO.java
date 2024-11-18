package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.Delays;





public interface DelaysDAO extends GenericDAO<Delays, Integer> {
  
	List<Delays> findAll();
	






}


