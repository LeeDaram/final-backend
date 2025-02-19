package com.example.finalEclips.eclips.store.service;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.finalEclips.eclips.store.dto.IndustryDto;
import com.example.finalEclips.eclips.store.dto.SidoDto;
import com.example.finalEclips.eclips.store.dto.SigunguDto;
import com.example.finalEclips.eclips.store.dto.StoreDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDto;

public interface StoreService {
    PageImpl<StoreDto> getStores(StoreRequestDto storeRequestDto, Pageable pageable); // 착한가격업소 모든 정보 조회
    List<SidoDto> getSido(); // 시도 데이터
    List<SigunguDto> getSigungu(); // 시군구 데이터
    List<IndustryDto> getIndustry(); // 업종명 데이터
}
