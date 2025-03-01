package com.example.finalEclips.eclips.mypage.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ReviewDto {

    private int review_id;
    private int store_id;
    private String user_id;
    private int rating;
    private String content;
    private String review_menu;
    private int review_price;
    private int like_count;
    private LocalDateTime created_at;

}
