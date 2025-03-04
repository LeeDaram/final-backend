package com.example.finalEclips.eclips.averageprice.service;

import com.example.finalEclips.eclips.averageprice.dto.AveragePriceDto;
import com.example.finalEclips.eclips.averageprice.dto.LocationDto;

public interface AveragePriceService {

    // 평균가격 조회
    int getAveragePrice(AveragePriceDto averagePriceDto);

    // 사용자 지역 조회
    LocationDto getLocationByUserId(String userId);

}
