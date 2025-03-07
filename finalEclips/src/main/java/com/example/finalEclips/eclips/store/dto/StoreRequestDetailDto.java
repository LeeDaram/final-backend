package com.example.finalEclips.eclips.store.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StoreRequestDetailDto {
	private String userId;
	private int storeId;
	private LocalDate reservationDate;
}
