package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.Lounges;





public interface LoungesDAO extends GenericDAO<Lounges, Integer> {
  
	List<Lounges> findAll();
	






}


