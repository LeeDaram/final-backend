package com.example.finalEclips.eclips.guide.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.finalEclips.eclips.guide.dto.Guide;
import com.example.finalEclips.eclips.guide.service.GuideService;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/guides")
@RequiredArgsConstructor
public class GuideController {
	private final GuideService guideService;
	
	private static final String IMAGE_DIRECTORY = "C:/fileupload/src/main/resources/static/image"; // 이미지 저장 경로
	private static final String IMAGE_BASE_URL = "http://localhost:8080/uploads/";
	
	@GetMapping("/all")
    public List<Guide> getAllGuides(){
		return guideService.getAllGuides().stream().map(this::updateImageUrl).collect(Collectors.toList());
	}
	@GetMapping("/uploads/{imageName}")
	public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
		try {
			Path imagePath = Paths.get(IMAGE_DIRECTORY).resolve(imageName).normalize();
			Resource resource = new UrlResource(imagePath.toUri());

			if (resource.exists()) {
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) // PNG 사용 시 IMAGE_PNG 변경
						.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
						.body(resource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

//    이미지 URL을 변환하여 클라이언트에 반환 (공통 로직)

	private Guide updateImageUrl(Guide guide) {
		if (guide != null && guide.getImageUrl() != null) {
			guide.setImageUrl(IMAGE_BASE_URL + guide.getImageUrl());
		}
		return guide;
	}

//    클라이언트에서 받은 이미지 URL을 내부 경로로 변환 (공통 로직)

	private String cleanImageUrl(String imageUrl) {
		return (imageUrl != null) ? imageUrl.replace(IMAGE_BASE_URL, "") : null;
	}
}
	



