package com.skybay888.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TicketPricePageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<TicketPriceDTO> ticketPrices;
}




