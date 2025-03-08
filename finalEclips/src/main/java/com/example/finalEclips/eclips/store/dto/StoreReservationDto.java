package com.example.finalEclips.eclips.store.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StoreReservationDto {
    private int storeId;
    private int reviewId;
    private String userId;
    private int rating;
    private String content;
    private String reviewMenu;
    private int price;
    private int likeCount;
    private int reviewCount;
    private float rationgCount;
    private String img;
    private LocalDate createdAt;
}
