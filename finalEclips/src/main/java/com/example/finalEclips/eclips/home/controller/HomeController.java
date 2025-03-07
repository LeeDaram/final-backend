package com.example.finalEclips.eclips.home.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.home.dto.HomeCountDto;
import com.example.finalEclips.eclips.home.dto.HomeNoticeDto;
import com.example.finalEclips.eclips.home.dto.LikeCountTopDto;
import com.example.finalEclips.eclips.home.service.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {
	private final HomeService homeService;
	
	@GetMapping("/likecount")
	public ResponseEntity<List<LikeCountTopDto>> getAllTodayShop(){
		return ResponseEntity.ok(homeService.getAllTodayShop());
	}
	
	@GetMapping("/homecount")
	public ResponseEntity<List<HomeCountDto>> getAllTotalCount(){
		return ResponseEntity.ok(homeService.getAllTotalCount());
	}
	
	@GetMapping("/newnotice")
	public ResponseEntity<List<HomeNoticeDto>> getAllNotices(){
		return ResponseEntity.ok(homeService.getAllNotices());
	}
	

}
