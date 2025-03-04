package com.example.finalEclips.eclips.store.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StoreDetailDto {
    private int storeId;
    private int reviewId;
    private int userId;
    private int rating;
    private String content;
    private String reviewMenu;
    private int price;
    private int likeCount;
    private LocalDate createdAt;
}
