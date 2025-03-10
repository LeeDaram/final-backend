package com.example.finalEclips.eclips.store.dto;

import lombok.Data;

@Data
public class ReviewRequestDto {
	private int reviewId;
	private int storeId;
	private int isLiked;
	private String userId;
}
