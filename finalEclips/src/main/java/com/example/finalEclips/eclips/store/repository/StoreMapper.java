package com.example.finalEclips.eclips.store.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.finalEclips.eclips.common.dto.PaginationDto;
import com.example.finalEclips.eclips.store.dto.StoreDto;

@Mapper
public interface StoreMapper {
    List<StoreDto> findPaginatedStores(PaginationDto<?> paginationDto);
    int findPaginatedStoresCount(PaginationDto<?> paginationDto);
}
