package com.skybay888.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DelaysPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<DelaysDTO> delayss;
}





