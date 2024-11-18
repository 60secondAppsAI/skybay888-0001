package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.RouteMap;





public interface RouteMapDAO extends GenericDAO<RouteMap, Integer> {
  
	List<RouteMap> findAll();
	






}


