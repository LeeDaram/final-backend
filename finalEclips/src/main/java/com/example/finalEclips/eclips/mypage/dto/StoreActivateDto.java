package com.example.finalEclips.eclips.mypage.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StoreActivateDto {

    private int activateId;
    private String userId;
    private LocalDate activateDate;
    private String name;
    private String phoneNumber;

}
