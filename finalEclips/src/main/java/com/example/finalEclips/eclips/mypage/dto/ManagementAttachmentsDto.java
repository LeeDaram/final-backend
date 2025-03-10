package com.example.finalEclips.eclips.mypage.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ManagementAttachmentsDto {

    private int attachmentId;
    private int storeId;
    private String originFilename;
    private String storedFilename;
    private String contentType;
    private int fileSize;
    private LocalDate createdAt;

}
