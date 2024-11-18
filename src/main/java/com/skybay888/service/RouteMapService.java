package com.skybay888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay888.domain.RouteMap;
import com.skybay888.dto.RouteMapDTO;
import com.skybay888.dto.RouteMapSearchDTO;
import com.skybay888.dto.RouteMapPageDTO;
import com.skybay888.dto.RouteMapConvertCriteriaDTO;
import com.skybay888.service.GenericService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RouteMapService extends GenericService<RouteMap, Integer> {

	List<RouteMap> findAll();

	ResultDTO addRouteMap(RouteMapDTO routeMapDTO, RequestDTO requestDTO);

	ResultDTO updateRouteMap(RouteMapDTO routeMapDTO, RequestDTO requestDTO);

    Page<RouteMap> getAllRouteMaps(Pageable pageable);

    Page<RouteMap> getAllRouteMaps(Specification<RouteMap> spec, Pageable pageable);

	ResponseEntity<RouteMapPageDTO> getRouteMaps(RouteMapSearchDTO routeMapSearchDTO);
	
	List<RouteMapDTO> convertRouteMapsToRouteMapDTOs(List<RouteMap> routeMaps, RouteMapConvertCriteriaDTO convertCriteria);

	RouteMapDTO getRouteMapDTOById(Integer routeMapId);







}





