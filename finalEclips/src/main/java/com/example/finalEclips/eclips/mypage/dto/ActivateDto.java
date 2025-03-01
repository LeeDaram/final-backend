package com.example.finalEclips.eclips.mypage.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ActivateDto {

    private int activateId;
    private String userId;
    private int storeId;
    private LocalDate activateDate;
    private LocalDate createdAt;
    private String storeName;

}
