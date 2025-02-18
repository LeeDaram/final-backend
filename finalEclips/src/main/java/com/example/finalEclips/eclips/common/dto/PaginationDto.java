package com.example.finalEclips.eclips.common.dto;

import org.springframework.data.domain.Pageable;

import lombok.Builder;

@Builder
public class PaginationDto<T> {
    private T data;
    private Pageable pageable;
}
