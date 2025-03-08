package com.example.finalEclips.eclips.qna.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class QnaPaginationDto {
	private int questionId;
	private String userId;
	private String title;
	private String content;
	private LocalDate createdAt;
	private int views;
	private int answerCount;
}
