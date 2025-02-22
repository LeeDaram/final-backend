package com.example.finalEclips.eclips.faq.dto;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;

@Data
public class FaqDto {
	private int faqId;
	private String title;
	private String contents;
	private LocalDate createdAt;
	private LocalDate updatedAt;
}
