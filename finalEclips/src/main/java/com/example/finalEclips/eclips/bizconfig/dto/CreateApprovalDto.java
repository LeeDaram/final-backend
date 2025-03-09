package com.example.finalEclips.eclips.bizconfig.dto;

import lombok.Data;

@Data
public class CreateApprovalDto {
	private int storeId;
	private String mainMenu;
	private int price;
	private char priceApproval;
	private char cleanlinessApproval;
	private String finalApprovalStatus;
}
