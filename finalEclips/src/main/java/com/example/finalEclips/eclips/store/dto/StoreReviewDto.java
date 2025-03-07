package com.example.finalEclips.eclips.store.dto;

import java.time.LocalTime;

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
	private LocalTime createdAt;
	private String imageIds;
}
