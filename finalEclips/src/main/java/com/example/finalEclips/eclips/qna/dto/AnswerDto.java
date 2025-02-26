package com.example.finalEclips.eclips.qna.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AnswerDto {
	private int questionId;
	private String content;
	private LocalDate createdAt;
	private LocalDate updateAt;
}
