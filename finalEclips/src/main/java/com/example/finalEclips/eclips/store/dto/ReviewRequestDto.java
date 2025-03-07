package com.example.finalEclips.eclips.store.dto;

import java.util.List;

import com.example.finalEclips.eclips.common.dto.FileDto;

import lombok.Data;

@Data
public class ReviewRequestDto {
	private int rating;
	private int cost;
	private String review;
	private List<FileDto> fileDto;
}
