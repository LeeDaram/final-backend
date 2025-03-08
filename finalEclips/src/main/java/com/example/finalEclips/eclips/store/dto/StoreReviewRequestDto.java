package com.example.finalEclips.eclips.store.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StoreReviewRequestDto {
	private int storeId;
	private int sort;
}
