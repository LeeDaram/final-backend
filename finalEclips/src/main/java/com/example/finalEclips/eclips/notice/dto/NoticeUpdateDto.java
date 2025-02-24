package com.example.finalEclips.eclips.notice.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NoticeUpdateDto {
	
	private int id;
	private String title;
	private String content;
	private LocalDate updatedAt;
}
