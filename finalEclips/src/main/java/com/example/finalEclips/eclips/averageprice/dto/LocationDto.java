package com.example.finalEclips.eclips.averageprice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class LocationDto {
    @JsonIgnore
    private String userId;

    private String sidoName;

    private String sigunguName;
}
