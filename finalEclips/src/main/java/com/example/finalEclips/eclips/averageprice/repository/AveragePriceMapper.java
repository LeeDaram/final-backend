package com.example.finalEclips.eclips.averageprice.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.finalEclips.eclips.averageprice.dto.AveragePriceDto;
import com.example.finalEclips.eclips.averageprice.dto.LocationDto;

@Mapper
public interface AveragePriceMapper {

    // 평균가격 조회
    int getAveragePrice(AveragePriceDto averagePriceDto);

    // 사용자 지역 조회
    LocationDto findLocationByUserId(@Param("userId") String userId);

}
