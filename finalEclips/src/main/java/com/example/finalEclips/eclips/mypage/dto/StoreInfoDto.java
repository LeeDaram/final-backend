package com.example.finalEclips.eclips.mypage.dto;

import lombok.Data;

@Data
public class StoreInfoDto {

    private int storeId;
    private String mainMenu;
    private int price;
    private String storeImage;
    private char priceApproval;
    private char cleanlinessApproval;
    private String finalApprovalStatus;
    private int businessRegNo;
    private String storeName;
    private String address;
    private String sidoName;
    private String sigunguName;

}
