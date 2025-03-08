package com.example.finalEclips.eclips.notice.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NoticePaginationDto {
	private int noticeId;
	private String title;
	private String content;
	private String isMainNotice;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private int views;
	private int attachmentsCount;
}
