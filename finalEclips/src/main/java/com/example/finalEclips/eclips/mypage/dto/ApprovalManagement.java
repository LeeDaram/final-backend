package com.example.finalEclips.eclips.mypage.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ApprovalManagement {

    private int store_id;
    private String main_menu;
    private int price;
    private String store_image;
    private char price_approval;
    private char cleanliness_approval;
    private String final_approval_status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
