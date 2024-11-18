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
import com.skybay888.dao.RouteMapDAO;
import com.skybay888.domain.RouteMap;
import com.skybay888.dto.RouteMapDTO;
import com.skybay888.dto.RouteMapSearchDTO;
import com.skybay888.dto.RouteMapPageDTO;
import com.skybay888.dto.RouteMapConvertCriteriaDTO;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import com.skybay888.service.RouteMapService;
import com.skybay888.util.ControllerUtils;





@Service
public class RouteMapServiceImpl extends GenericServiceImpl<RouteMap, Integer> implements RouteMapService {

    private final static Logger logger = LoggerFactory.getLogger(RouteMapServiceImpl.class);

	@Autowired
	RouteMapDAO routeMapDao;

	


	@Override
	public GenericDAO<RouteMap, Integer> getDAO() {
		return (GenericDAO<RouteMap, Integer>) routeMapDao;
	}
	
	public List<RouteMap> findAll () {
		List<RouteMap> routeMaps = routeMapDao.findAll();
		
		return routeMaps;	
		
	}

	public ResultDTO addRouteMap(RouteMapDTO routeMapDTO, RequestDTO requestDTO) {

		RouteMap routeMap = new RouteMap();

		routeMap.setRouteMapId(routeMapDTO.getRouteMapId());


		routeMap.setDistance(routeMapDTO.getDistance());


		routeMap.setDuration(routeMapDTO.getDuration());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		routeMap = routeMapDao.save(routeMap);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<RouteMap> getAllRouteMaps(Pageable pageable) {
		return routeMapDao.findAll(pageable);
	}

	public Page<RouteMap> getAllRouteMaps(Specification<RouteMap> spec, Pageable pageable) {
		return routeMapDao.findAll(spec, pageable);
	}

	public ResponseEntity<RouteMapPageDTO> getRouteMaps(RouteMapSearchDTO routeMapSearchDTO) {
	
			Integer routeMapId = routeMapSearchDTO.getRouteMapId(); 
   			String sortBy = routeMapSearchDTO.getSortBy();
			String sortOrder = routeMapSearchDTO.getSortOrder();
			String searchQuery = routeMapSearchDTO.getSearchQuery();
			Integer page = routeMapSearchDTO.getPage();
			Integer size = routeMapSearchDTO.getSize();

	        Specification<RouteMap> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, routeMapId, "routeMapId"); 
			
			
			

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

		Page<RouteMap> routeMaps = this.getAllRouteMaps(spec, pageable);
		
		//System.out.println(String.valueOf(routeMaps.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(routeMaps.getTotalPages()));
		
		List<RouteMap> routeMapsList = routeMaps.getContent();
		
		RouteMapConvertCriteriaDTO convertCriteria = new RouteMapConvertCriteriaDTO();
		List<RouteMapDTO> routeMapDTOs = this.convertRouteMapsToRouteMapDTOs(routeMapsList,convertCriteria);
		
		RouteMapPageDTO routeMapPageDTO = new RouteMapPageDTO();
		routeMapPageDTO.setRouteMaps(routeMapDTOs);
		routeMapPageDTO.setTotalElements(routeMaps.getTotalElements());
		return ResponseEntity.ok(routeMapPageDTO);
	}

	public List<RouteMapDTO> convertRouteMapsToRouteMapDTOs(List<RouteMap> routeMaps, RouteMapConvertCriteriaDTO convertCriteria) {
		
		List<RouteMapDTO> routeMapDTOs = new ArrayList<RouteMapDTO>();
		
		for (RouteMap routeMap : routeMaps) {
			routeMapDTOs.add(convertRouteMapToRouteMapDTO(routeMap,convertCriteria));
		}
		
		return routeMapDTOs;

	}
	
	public RouteMapDTO convertRouteMapToRouteMapDTO(RouteMap routeMap, RouteMapConvertCriteriaDTO convertCriteria) {
		
		RouteMapDTO routeMapDTO = new RouteMapDTO();
		
		routeMapDTO.setRouteMapId(routeMap.getRouteMapId());

	
		routeMapDTO.setDistance(routeMap.getDistance());

	
		routeMapDTO.setDuration(routeMap.getDuration());

	

		
		return routeMapDTO;
	}

	public ResultDTO updateRouteMap(RouteMapDTO routeMapDTO, RequestDTO requestDTO) {
		
		RouteMap routeMap = routeMapDao.getById(routeMapDTO.getRouteMapId());

		routeMap.setRouteMapId(ControllerUtils.setValue(routeMap.getRouteMapId(), routeMapDTO.getRouteMapId()));

		routeMap.setDistance(ControllerUtils.setValue(routeMap.getDistance(), routeMapDTO.getDistance()));

		routeMap.setDuration(ControllerUtils.setValue(routeMap.getDuration(), routeMapDTO.getDuration()));



        routeMap = routeMapDao.save(routeMap);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public RouteMapDTO getRouteMapDTOById(Integer routeMapId) {
	
		RouteMap routeMap = routeMapDao.getById(routeMapId);
			
		
		RouteMapConvertCriteriaDTO convertCriteria = new RouteMapConvertCriteriaDTO();
		return(this.convertRouteMapToRouteMapDTO(routeMap,convertCriteria));
	}







}
