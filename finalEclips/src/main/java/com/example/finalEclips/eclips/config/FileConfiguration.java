package com.example.finalEclips.eclips.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;


@Configuration
@Getter
public class FileConfiguration {
	
	@Value("${attachment.dir-path}")
	private String dirPath;
}
