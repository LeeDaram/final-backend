package com.example.finalEclips.eclips.store.dto;

import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class StoreFilterDto {
	private int sidoId;
	private String sidoName;
	private int sigunguId;
	private String sigunguName;
	private int industryId;
	private String industryName;
	private String takeout;
	private String delivery;
	private String wifi;
	private String pet;
	private String kid;
	private String parking;
	private Pageable pageable;
}
