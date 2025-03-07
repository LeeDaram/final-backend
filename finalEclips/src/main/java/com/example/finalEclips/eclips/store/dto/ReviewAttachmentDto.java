package com.example.finalEclips.eclips.store.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ReviewAttachmentDto {
	private int reviewId;
	private int reviewImgId;
	private String originFilename;
	private String storedFilename;
	private String contentType;
	private int fileSize;
	private LocalDate createdAt;
}
