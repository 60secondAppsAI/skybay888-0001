package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.Promotions;





public interface PromotionsDAO extends GenericDAO<Promotions, Integer> {
  
	List<Promotions> findAll();
	






}

