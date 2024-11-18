package com.skybay888.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybay888.domain.Meal;
import com.skybay888.dto.MealDTO;
import com.skybay888.dto.MealSearchDTO;
import com.skybay888.dto.MealPageDTO;
import com.skybay888.dto.MealConvertCriteriaDTO;
import com.skybay888.service.GenericService;
import com.skybay888.dto.common.RequestDTO;
import com.skybay888.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MealService extends GenericService<Meal, Integer> {

	List<Meal> findAll();

	ResultDTO addMeal(MealDTO mealDTO, RequestDTO requestDTO);

	ResultDTO updateMeal(MealDTO mealDTO, RequestDTO requestDTO);

    Page<Meal> getAllMeals(Pageable pageable);

    Page<Meal> getAllMeals(Specification<Meal> spec, Pageable pageable);

	ResponseEntity<MealPageDTO> getMeals(MealSearchDTO mealSearchDTO);
	
	List<MealDTO> convertMealsToMealDTOs(List<Meal> meals, MealConvertCriteriaDTO convertCriteria);

	MealDTO getMealDTOById(Integer mealId);







}





