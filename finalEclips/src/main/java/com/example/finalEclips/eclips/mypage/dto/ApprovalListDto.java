package com.example.finalEclips.eclips.mypage.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ApprovalListDto {

    private int storeId;
    private String storeName;
    private String mainMenu;
    private String sidoName;
    private int price;
    private String finalApprovalStatus;
    private LocalDate updatedAt;

}
