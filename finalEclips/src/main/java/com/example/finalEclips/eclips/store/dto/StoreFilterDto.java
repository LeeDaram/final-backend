package com.example.finalEclips.eclips.store.dto;

import lombok.Data;

@Data
public class StoreFilterDto {
	private int sidoId;
	private String sidoName;
	private int sigunguId;
	private int storeId;
	private int reviewId;
	private String userId;
	private String sigunguName;
	private int industryId;
	private String industryName;
	private String storeImage;
	private String storeName;
	private String mainMenu;
	private float reviewAvg;
	private int reviewCount;
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
    private int rating5Count;
    private int rating4Count;
    private int rating3Count;
    private int rating2Count;
    private int rating1Count;
}
