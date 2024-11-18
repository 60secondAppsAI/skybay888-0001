package com.skybay888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay888.domain.TicketPrice;
import com.skybay888.dto.TicketPriceDTO;
import com.skybay888.dto.TicketPriceSearchDTO;
import com.skybay888.dto.TicketPricePageDTO;
import com.skybay888.dto.TicketPriceConvertCriteriaDTO;
import com.skybay888.service.GenericService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface TicketPriceService extends GenericService<TicketPrice, Integer> {

	List<TicketPrice> findAll();

	ResultDTO addTicketPrice(TicketPriceDTO ticketPriceDTO, RequestDTO requestDTO);

	ResultDTO updateTicketPrice(TicketPriceDTO ticketPriceDTO, RequestDTO requestDTO);

    Page<TicketPrice> getAllTicketPrices(Pageable pageable);

    Page<TicketPrice> getAllTicketPrices(Specification<TicketPrice> spec, Pageable pageable);

	ResponseEntity<TicketPricePageDTO> getTicketPrices(TicketPriceSearchDTO ticketPriceSearchDTO);
	
	List<TicketPriceDTO> convertTicketPricesToTicketPriceDTOs(List<TicketPrice> ticketPrices, TicketPriceConvertCriteriaDTO convertCriteria);

	TicketPriceDTO getTicketPriceDTOById(Integer ticketPriceId);







}





