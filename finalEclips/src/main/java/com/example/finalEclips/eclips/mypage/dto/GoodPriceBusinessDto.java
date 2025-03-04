package com.example.finalEclips.eclips.mypage.dto;

import lombok.Data;

@Data
public class GoodPriceBusinessDto {

    private String userId;
    private int industryId;
    private int contact;
    private String storeImage;
    private int averageRating;
    private char parking;
    private char takeout;
    private char delivery;
    private char wifi;
    private char pet;
    private char kid;

}
