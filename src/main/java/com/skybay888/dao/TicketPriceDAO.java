package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.TicketPrice;





public interface TicketPriceDAO extends GenericDAO<TicketPrice, Integer> {
  
	List<TicketPrice> findAll();
	






}


