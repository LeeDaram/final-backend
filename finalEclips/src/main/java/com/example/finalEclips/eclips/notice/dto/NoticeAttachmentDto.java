package com.example.finalEclips.eclips.notice.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class NoticeAttachmentDto {
	private int attachmentId;
	private int noticeId;
	private String originFilename;
    private String storedFilename;
    private String contentType;
    private int fileSize;
    private LocalDate createdAt;
}
