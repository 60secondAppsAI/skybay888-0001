package com.skybay888.dao;

import java.util.List;

import com.skybay888.dao.GenericDAO;
import com.skybay888.domain.PaymentDetail;





public interface PaymentDetailDAO extends GenericDAO<PaymentDetail, Integer> {
  
	List<PaymentDetail> findAll();
	






}


