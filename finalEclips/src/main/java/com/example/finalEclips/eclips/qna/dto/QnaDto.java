package com.example.finalEclips.eclips.qna.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class QnaDto {
	private int questionId;
	private String userId;
	private String title;
	private String content;
	private LocalDate createdAt;
	private int views;
	private List<AnswerDto> answers;
}
