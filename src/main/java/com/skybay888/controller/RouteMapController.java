package com.skybay888.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skybay888.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skybay888.domain.RouteMap;
import com.skybay888.dto.RouteMapDTO;
import com.skybay888.dto.RouteMapSearchDTO;
import com.skybay888.dto.RouteMapPageDTO;
import com.skybay888.service.RouteMapService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/routeMap")
@RestController
public class RouteMapController {

	private final static Logger logger = LoggerFactory.getLogger(RouteMapController.class);

	@Autowired
	RouteMapService routeMapService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<RouteMap> getAll() {

		List<RouteMap> routeMaps = routeMapService.findAll();
		
		return routeMaps;	
	}

	@GetMapping(value = "/{routeMapId}")
	@ResponseBody
	public RouteMapDTO getRouteMap(@PathVariable Integer routeMapId) {
		
		return (routeMapService.getRouteMapDTOById(routeMapId));
	}

 	@RequestMapping(value = "/addRouteMap", method = RequestMethod.POST)
	public ResponseEntity<?> addRouteMap(@RequestBody RouteMapDTO routeMapDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = routeMapService.addRouteMap(routeMapDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/routeMaps")
	public ResponseEntity<RouteMapPageDTO> getRouteMaps(RouteMapSearchDTO routeMapSearchDTO) {
 
		return routeMapService.getRouteMaps(routeMapSearchDTO);
	}	

	@RequestMapping(value = "/updateRouteMap", method = RequestMethod.POST)
	public ResponseEntity<?> updateRouteMap(@RequestBody RouteMapDTO routeMapDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = routeMapService.updateRouteMap(routeMapDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
