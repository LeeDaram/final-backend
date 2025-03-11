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
	
	
	@GetMapping("/all")
    public List<Guide> getAllGuides(){
		return guideService.getAllGuides();
	}
	
//	@GetMapping("/uploads/{imageName}")
//	public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
//		try {
//			Path imagePath = Paths.get(IMAGE_DIRECTORY).resolve(imageName).normalize();
//			Resource resource = new UrlResource(imagePath.toUri());
//
//			if (resource.exists()) {
//				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG) // PNG 사용 시 IMAGE_PNG 변경
//						.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//						.body(resource);
//			} else {
//				return ResponseEntity.notFound().build();
//			}
//		} catch (Exception e) {
//			return ResponseEntity.internalServerError().build();
//		}
//	}
}
	



