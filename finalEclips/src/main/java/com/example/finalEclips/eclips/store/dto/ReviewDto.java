package com.example.finalEclips.eclips.store.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto {
	private int reviewId; // 리뷰아이디를 mybatis에서 한 번 걸쳤다가 받아와야됨
	private int storeId;
	private String userId;
	private int rating;
	private String review;
	private String menu;
	private int cost;
	
}
