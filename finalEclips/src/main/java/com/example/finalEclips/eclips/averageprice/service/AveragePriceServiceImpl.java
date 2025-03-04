package com.example.finalEclips.eclips.averageprice.service;

import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.averageprice.dto.AveragePriceDto;
import com.example.finalEclips.eclips.averageprice.dto.LocationDto;
import com.example.finalEclips.eclips.averageprice.repository.AveragePriceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AveragePriceServiceImpl implements AveragePriceService {

    private final AveragePriceMapper averagePriceMapper;

    // 평균가격 조회
    @Override
    public int getAveragePrice(AveragePriceDto averagePriceDto) {
        return averagePriceMapper.getAveragePrice(averagePriceDto);
    }

    // 사용자 지역 조회
    @Override
    public LocationDto getLocationByUserId(String userId) {
        return averagePriceMapper.findLocationByUserId(userId);
    }

}
