package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.Crew;





public interface CrewDAO extends GenericDAO<Crew, Integer> {
  
	List<Crew> findAll();
	






}


