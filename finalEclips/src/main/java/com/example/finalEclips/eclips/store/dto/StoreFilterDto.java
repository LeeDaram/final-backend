package com.example.finalEclips.eclips.store.dto;

import lombok.Data;

@Data
public class StoreFilterDto {
	private int sidoId;
	private String sidoName;
	private int sigunguId;
	private int storeId;
	private String sigunguName;
	private int industryId;
	private String industryName;
	private String storeImage;
	private String storeName;
	private String mainMenu;
	private float averageRating;
	private int likeCount;
	private int price;
	private String contact;
	private String takeout;
	private String delivery;
	private String wifi;
	private String pet;
	private String kid;
	private String parking;
	private String address;
	private float lat;
	private float lng;
}
