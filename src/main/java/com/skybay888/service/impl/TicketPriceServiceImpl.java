package com.skybay888.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skybay888.dao.GenericDAO;
import com.skybay888.service.GenericService;
import com.skybay888.service.impl.GenericServiceImpl;
import com.skybay888.dao.TicketPriceDAO;
import com.skybay888.domain.TicketPrice;
import com.skybay888.dto.TicketPriceDTO;
import com.skybay888.dto.TicketPriceSearchDTO;
import com.skybay888.dto.TicketPricePageDTO;
import com.skybay888.dto.TicketPriceConvertCriteriaDTO;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import com.skybay888.service.TicketPriceService;
import com.skybay888.util.ControllerUtils;





@Service
public class TicketPriceServiceImpl extends GenericServiceImpl<TicketPrice, Integer> implements TicketPriceService {

    private final static Logger logger = LoggerFactory.getLogger(TicketPriceServiceImpl.class);

	@Autowired
	TicketPriceDAO ticketPriceDao;

	


	@Override
	public GenericDAO<TicketPrice, Integer> getDAO() {
		return (GenericDAO<TicketPrice, Integer>) ticketPriceDao;
	}
	
	public List<TicketPrice> findAll () {
		List<TicketPrice> ticketPrices = ticketPriceDao.findAll();
		
		return ticketPrices;	
		
	}

	public ResultDTO addTicketPrice(TicketPriceDTO ticketPriceDTO, RequestDTO requestDTO) {

		TicketPrice ticketPrice = new TicketPrice();

		ticketPrice.setTicketPriceId(ticketPriceDTO.getTicketPriceId());


		ticketPrice.setPrice(ticketPriceDTO.getPrice());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		ticketPrice = ticketPriceDao.save(ticketPrice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<TicketPrice> getAllTicketPrices(Pageable pageable) {
		return ticketPriceDao.findAll(pageable);
	}

	public Page<TicketPrice> getAllTicketPrices(Specification<TicketPrice> spec, Pageable pageable) {
		return ticketPriceDao.findAll(spec, pageable);
	}

	public ResponseEntity<TicketPricePageDTO> getTicketPrices(TicketPriceSearchDTO ticketPriceSearchDTO) {
	
			Integer ticketPriceId = ticketPriceSearchDTO.getTicketPriceId(); 
  			String sortBy = ticketPriceSearchDTO.getSortBy();
			String sortOrder = ticketPriceSearchDTO.getSortOrder();
			String searchQuery = ticketPriceSearchDTO.getSearchQuery();
			Integer page = ticketPriceSearchDTO.getPage();
			Integer size = ticketPriceSearchDTO.getSize();

	        Specification<TicketPrice> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, ticketPriceId, "ticketPriceId"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<TicketPrice> ticketPrices = this.getAllTicketPrices(spec, pageable);
		
		//System.out.println(String.valueOf(ticketPrices.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(ticketPrices.getTotalPages()));
		
		List<TicketPrice> ticketPricesList = ticketPrices.getContent();
		
		TicketPriceConvertCriteriaDTO convertCriteria = new TicketPriceConvertCriteriaDTO();
		List<TicketPriceDTO> ticketPriceDTOs = this.convertTicketPricesToTicketPriceDTOs(ticketPricesList,convertCriteria);
		
		TicketPricePageDTO ticketPricePageDTO = new TicketPricePageDTO();
		ticketPricePageDTO.setTicketPrices(ticketPriceDTOs);
		ticketPricePageDTO.setTotalElements(ticketPrices.getTotalElements());
		return ResponseEntity.ok(ticketPricePageDTO);
	}

	public List<TicketPriceDTO> convertTicketPricesToTicketPriceDTOs(List<TicketPrice> ticketPrices, TicketPriceConvertCriteriaDTO convertCriteria) {
		
		List<TicketPriceDTO> ticketPriceDTOs = new ArrayList<TicketPriceDTO>();
		
		for (TicketPrice ticketPrice : ticketPrices) {
			ticketPriceDTOs.add(convertTicketPriceToTicketPriceDTO(ticketPrice,convertCriteria));
		}
		
		return ticketPriceDTOs;

	}
	
	public TicketPriceDTO convertTicketPriceToTicketPriceDTO(TicketPrice ticketPrice, TicketPriceConvertCriteriaDTO convertCriteria) {
		
		TicketPriceDTO ticketPriceDTO = new TicketPriceDTO();
		
		ticketPriceDTO.setTicketPriceId(ticketPrice.getTicketPriceId());

	
		ticketPriceDTO.setPrice(ticketPrice.getPrice());

	

		
		return ticketPriceDTO;
	}

	public ResultDTO updateTicketPrice(TicketPriceDTO ticketPriceDTO, RequestDTO requestDTO) {
		
		TicketPrice ticketPrice = ticketPriceDao.getById(ticketPriceDTO.getTicketPriceId());

		ticketPrice.setTicketPriceId(ControllerUtils.setValue(ticketPrice.getTicketPriceId(), ticketPriceDTO.getTicketPriceId()));

		ticketPrice.setPrice(ControllerUtils.setValue(ticketPrice.getPrice(), ticketPriceDTO.getPrice()));



        ticketPrice = ticketPriceDao.save(ticketPrice);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public TicketPriceDTO getTicketPriceDTOById(Integer ticketPriceId) {
	
		TicketPrice ticketPrice = ticketPriceDao.getById(ticketPriceId);
			
		
		TicketPriceConvertCriteriaDTO convertCriteria = new TicketPriceConvertCriteriaDTO();
		return(this.convertTicketPriceToTicketPriceDTO(ticketPrice,convertCriteria));
	}







}
