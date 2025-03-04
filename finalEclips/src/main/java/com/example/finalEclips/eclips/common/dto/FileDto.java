package com.example.finalEclips.eclips.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FileDto {
	private String originFilename;
    private String storedFilename;
    private String contentType;
    private long size;
}
