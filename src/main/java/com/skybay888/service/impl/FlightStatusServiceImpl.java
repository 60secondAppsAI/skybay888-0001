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
import com.skybay888.dao.FlightStatusDAO;
import com.skybay888.domain.FlightStatus;
import com.skybay888.dto.FlightStatusDTO;
import com.skybay888.dto.FlightStatusSearchDTO;
import com.skybay888.dto.FlightStatusPageDTO;
import com.skybay888.dto.FlightStatusConvertCriteriaDTO;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import com.skybay888.service.FlightStatusService;
import com.skybay888.util.ControllerUtils;





@Service
public class FlightStatusServiceImpl extends GenericServiceImpl<FlightStatus, Integer> implements FlightStatusService {

    private final static Logger logger = LoggerFactory.getLogger(FlightStatusServiceImpl.class);

	@Autowired
	FlightStatusDAO flightStatusDao;

	


	@Override
	public GenericDAO<FlightStatus, Integer> getDAO() {
		return (GenericDAO<FlightStatus, Integer>) flightStatusDao;
	}
	
	public List<FlightStatus> findAll () {
		List<FlightStatus> flightStatuss = flightStatusDao.findAll();
		
		return flightStatuss;	
		
	}

	public ResultDTO addFlightStatus(FlightStatusDTO flightStatusDTO, RequestDTO requestDTO) {

		FlightStatus flightStatus = new FlightStatus();

		flightStatus.setFlightStatusId(flightStatusDTO.getFlightStatusId());


		flightStatus.setStatusDescription(flightStatusDTO.getStatusDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		flightStatus = flightStatusDao.save(flightStatus);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<FlightStatus> getAllFlightStatuss(Pageable pageable) {
		return flightStatusDao.findAll(pageable);
	}

	public Page<FlightStatus> getAllFlightStatuss(Specification<FlightStatus> spec, Pageable pageable) {
		return flightStatusDao.findAll(spec, pageable);
	}

	public ResponseEntity<FlightStatusPageDTO> getFlightStatuss(FlightStatusSearchDTO flightStatusSearchDTO) {
	
			Integer flightStatusId = flightStatusSearchDTO.getFlightStatusId(); 
 			String statusDescription = flightStatusSearchDTO.getStatusDescription(); 
 			String sortBy = flightStatusSearchDTO.getSortBy();
			String sortOrder = flightStatusSearchDTO.getSortOrder();
			String searchQuery = flightStatusSearchDTO.getSearchQuery();
			Integer page = flightStatusSearchDTO.getPage();
			Integer size = flightStatusSearchDTO.getSize();

	        Specification<FlightStatus> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, flightStatusId, "flightStatusId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, statusDescription, "statusDescription"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("statusDescription")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<FlightStatus> flightStatuss = this.getAllFlightStatuss(spec, pageable);
		
		//System.out.println(String.valueOf(flightStatuss.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(flightStatuss.getTotalPages()));
		
		List<FlightStatus> flightStatussList = flightStatuss.getContent();
		
		FlightStatusConvertCriteriaDTO convertCriteria = new FlightStatusConvertCriteriaDTO();
		List<FlightStatusDTO> flightStatusDTOs = this.convertFlightStatussToFlightStatusDTOs(flightStatussList,convertCriteria);
		
		FlightStatusPageDTO flightStatusPageDTO = new FlightStatusPageDTO();
		flightStatusPageDTO.setFlightStatuss(flightStatusDTOs);
		flightStatusPageDTO.setTotalElements(flightStatuss.getTotalElements());
		return ResponseEntity.ok(flightStatusPageDTO);
	}

	public List<FlightStatusDTO> convertFlightStatussToFlightStatusDTOs(List<FlightStatus> flightStatuss, FlightStatusConvertCriteriaDTO convertCriteria) {
		
		List<FlightStatusDTO> flightStatusDTOs = new ArrayList<FlightStatusDTO>();
		
		for (FlightStatus flightStatus : flightStatuss) {
			flightStatusDTOs.add(convertFlightStatusToFlightStatusDTO(flightStatus,convertCriteria));
		}
		
		return flightStatusDTOs;

	}
	
	public FlightStatusDTO convertFlightStatusToFlightStatusDTO(FlightStatus flightStatus, FlightStatusConvertCriteriaDTO convertCriteria) {
		
		FlightStatusDTO flightStatusDTO = new FlightStatusDTO();
		
		flightStatusDTO.setFlightStatusId(flightStatus.getFlightStatusId());

	
		flightStatusDTO.setStatusDescription(flightStatus.getStatusDescription());

	

		
		return flightStatusDTO;
	}

	public ResultDTO updateFlightStatus(FlightStatusDTO flightStatusDTO, RequestDTO requestDTO) {
		
		FlightStatus flightStatus = flightStatusDao.getById(flightStatusDTO.getFlightStatusId());

		flightStatus.setFlightStatusId(ControllerUtils.setValue(flightStatus.getFlightStatusId(), flightStatusDTO.getFlightStatusId()));

		flightStatus.setStatusDescription(ControllerUtils.setValue(flightStatus.getStatusDescription(), flightStatusDTO.getStatusDescription()));



        flightStatus = flightStatusDao.save(flightStatus);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public FlightStatusDTO getFlightStatusDTOById(Integer flightStatusId) {
	
		FlightStatus flightStatus = flightStatusDao.getById(flightStatusId);
			
		
		FlightStatusConvertCriteriaDTO convertCriteria = new FlightStatusConvertCriteriaDTO();
		return(this.convertFlightStatusToFlightStatusDTO(flightStatus,convertCriteria));
	}







}
