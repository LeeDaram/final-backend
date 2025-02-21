package com.example.finalEclips.eclips.faq.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.faq.dto.FaqDto;
import com.example.finalEclips.eclips.faq.service.FaqService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {
	private final FaqService faqService;
	
	@GetMapping("/all")
	public ResponseEntity<List<FaqDto>> getAllFaqs(){
		return ResponseEntity.ok(faqService.getAllFaqs());
	}
}
