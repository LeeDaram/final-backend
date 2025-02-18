package com.example.finalEclips.eclips.store.service;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.finalEclips.eclips.store.dto.StoreDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDto;

public interface StoreService {
    PageImpl<StoreDto> getStores(StoreRequestDto storeRequestDto, Pageable pageable); // 착한가격업소 모든 정보 조회
}
