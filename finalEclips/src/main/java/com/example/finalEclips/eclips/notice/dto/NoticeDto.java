package com.example.finalEclips.eclips.notice.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class NoticeDto {
	private int noticeId;
	private String title;
	private String content;
	private String isMainNotice;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private int views;
	private List<NoticeAttachmentDto> attachments;
//	TIMESTAMP 컬럼을 Java LocalDate로 받으면 시·분·초가 누락되거나 변환 문제가 생길 수 있으므로
//	필요에 따라 LocalDateTime으로 매핑하거나, DATE만 필요하다면 LocalDate를 쓰는지 확인한다.	
}
