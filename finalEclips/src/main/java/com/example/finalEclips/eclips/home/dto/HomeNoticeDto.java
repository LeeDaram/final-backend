package com.example.finalEclips.eclips.home.dto;

import lombok.Data;

@Data
public class HomeNoticeDto {
	private int noticeId;
	private String noticeTitle;
	private String noticeContent;
	private String isMainNotice;
}
