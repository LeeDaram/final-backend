package com.example.finalEclips.eclips.notice.dto;

import lombok.Data;

@Data
public class CreateNoticeDto {
	private int noticeId;
	private String title;
	private String content;
	private String isMainNotice;
	
}
