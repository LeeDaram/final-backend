package com.example.finalEclips.eclips.store.controller;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.store.dto.IndustryDto;
import com.example.finalEclips.eclips.store.dto.SidoDto;
import com.example.finalEclips.eclips.store.dto.SigunguDto;
import com.example.finalEclips.eclips.store.dto.StoreDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDto;
import com.example.finalEclips.eclips.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StoreController {
 private final StoreService storeService;
 
 	@GetMapping("/stores")
 	public ResponseEntity<PageImpl<StoreDto>> getStores(StoreRequestDto storeRequestDto, 
		 @PageableDefault(size = 8, page = 0) Pageable pageable){
     	return ResponseEntity.ok(storeService.getStores(storeRequestDto, pageable));
 	}
 
 	@GetMapping("/sido")
 	public ResponseEntity<List<SidoDto>> getSido(){
 		return ResponseEntity.ok(storeService.getSido());
	}
 	
 	@GetMapping("/sigungu")
 	public ResponseEntity<List<SigunguDto>> getSigungu(){
 		return ResponseEntity.ok(storeService.getSigungu());
 	}
 	
 	@GetMapping("/industry")
 	public ResponseEntity<List<IndustryDto>> gtIndustry(){
 		return ResponseEntity.ok(storeService.getIndustry());
 	}
}
