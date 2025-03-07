package com.example.finalEclips.eclips.mypage.dto;

import org.springframework.data.domain.Pageable;

import lombok.Builder;

@Builder
public class PaginationDto<T> {
    private T data;
    private Pageable pageable;
    private String period;
    private String userId;
}
