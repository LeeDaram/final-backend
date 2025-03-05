package com.example.finalEclips.eclips.store.controller;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.finalEclips.eclips.store.dto.FilterRequestDto;
import com.example.finalEclips.eclips.store.dto.IndustryDto;
import com.example.finalEclips.eclips.store.dto.SidoDto;
import com.example.finalEclips.eclips.store.dto.SigunguDto;
import com.example.finalEclips.eclips.store.dto.StoreAddressDto;
import com.example.finalEclips.eclips.store.dto.StoreDetailDto;
import com.example.finalEclips.eclips.store.dto.StoreDto;
import com.example.finalEclips.eclips.store.dto.StoreFilterDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDetailDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDto;
import com.example.finalEclips.eclips.store.service.StoreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StoreController {
 private final StoreService storeService;
 
// 	@GetMapping("/stores")
// 	public ResponseEntity<PageImpl<StoreDto>> getStores(StoreRequestDto storeRequestDto, 
//		 @PageableDefault(size = 8, page = 0) Pageable pageable){
//     	return ResponseEntity.ok(storeService.getStores(storeRequestDto, pageable));
// 	}
 
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
 	
 	@GetMapping("/stores")
 	public ResponseEntity<PageImpl<StoreFilterDto>> getFilterStore(FilterRequestDto filterRequestDto,
 			 @PageableDefault(size = 8, page = 0) Pageable pageable){
 		return ResponseEntity.ok(storeService.getStoreFilter(filterRequestDto, pageable));
 	}
 	
 	@GetMapping("/address")
 	public ResponseEntity<List<StoreAddressDto>> getAllAddress(){
 		return ResponseEntity.ok(storeService.getAllAddress());
 	}
 	
 	@GetMapping("/storeDetail")
 	public ResponseEntity<List<StoreDetailDto>> getStoreDetail(){
 		return ResponseEntity.ok(storeService.getAllStoreDetail());
 	}
 	
 	@PostMapping("/api/reservation")
 	public ResponseEntity<Void> createActivate(@RequestBody StoreDetailDto storeDetailDto){
 		storeService.createActivate(storeDetailDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(storeDetailDto.getUserId()).toUri();
        return ResponseEntity.created(uri).build();
 	}
 	
}
