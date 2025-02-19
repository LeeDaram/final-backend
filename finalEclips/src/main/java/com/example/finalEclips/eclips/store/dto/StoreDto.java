package com.example.finalEclips.eclips.store.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StoreDto {
    private int storeId;
    private String userId;
    private int industryId;
    private String contact;
    private String storeImage;
    private float averageRating;
    private String takeOut;
    private String delivery;
    private String wifi;
    private String pet;
    private String kid;
    private String parking;
    private String average;
    private String storeName;
    private String industryName;
    private String mainMenu;
    private int price;
    private String sigunguName;
    private String sidoName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
