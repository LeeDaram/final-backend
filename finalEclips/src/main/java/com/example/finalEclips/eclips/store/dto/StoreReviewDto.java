package com.example.finalEclips.eclips.store.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class StoreReviewDto {
	private int reviewId;
	private int storeId;
	private String userId;
	private int rating;
	private String content;
	private String reviewMenu;
	private int reviewPrice;
	private int likeCount;
	private LocalDateTime createdAt;
	private String imageIds;
	private int sort;
}
