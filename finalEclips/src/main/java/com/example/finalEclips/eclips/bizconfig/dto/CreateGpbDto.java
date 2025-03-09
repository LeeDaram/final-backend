package com.example.finalEclips.eclips.bizconfig.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateGpbDto {
	private int storeId;
	private String userId;
	private int industryId;
	private String contact;
	private String storeImage;
	private int averageRating;
	private char takeout;
	private char delivery;
	private char wifi;
	private char pet;
	private char kid;
	private char parking;
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
