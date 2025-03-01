package com.example.finalEclips.eclips.mypage.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class ReviewDto {

    private int reviewId;
    private int storeId;
    private String userId;
    private int rating;
    private String content;
    private String reviewMenu;
    private int reviewPrice;
    private int likeCount;
    private LocalDate createdAt;
    private String storeName;
    private List<String> reviewImg = new ArrayList<>();

}
