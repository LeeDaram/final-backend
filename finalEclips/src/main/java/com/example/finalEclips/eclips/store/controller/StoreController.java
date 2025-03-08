package com.example.finalEclips.eclips.store.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.store.dto.FilterRequestDto;
import com.example.finalEclips.eclips.store.dto.IndustryDto;
import com.example.finalEclips.eclips.store.dto.ReviewAttachmentDto;
import com.example.finalEclips.eclips.store.dto.ReviewDto;
import com.example.finalEclips.eclips.store.dto.ReviewRequestDto;
import com.example.finalEclips.eclips.store.dto.SidoDto;
import com.example.finalEclips.eclips.store.dto.SigunguDto;
import com.example.finalEclips.eclips.store.dto.StoreAddressDto;
import com.example.finalEclips.eclips.store.dto.StoreFilterDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDetailDto;
import com.example.finalEclips.eclips.store.dto.StoreReviewDto;
import com.example.finalEclips.eclips.store.dto.StoreReviewRequestDto;
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
 	
	
    @GetMapping("/store/reviews/{storeId}")
    public ResponseEntity<PageImpl<StoreReviewDto>> getStoreReviews(
    		@PageableDefault(size = 8, page = 0) Pageable pageable,
    		@PathVariable("storeId") int storeId,
    		@RequestParam(name = "sort", defaultValue = "0") int sort) {
    	
    	StoreReviewRequestDto requestDto = 
    			StoreReviewRequestDto.builder()
    			.storeId(storeId)
    			.sort(sort)
    			.build();
    	
        return ResponseEntity.ok(storeService.getStoreReviews(requestDto, pageable));
    }
 	
 	@GetMapping("/address")
 	public ResponseEntity<List<StoreAddressDto>> getAllAddress(){
 		return ResponseEntity.ok(storeService.getAllAddress());
 	}
 	
 	@PostMapping("/api/reservation")
 	public ResponseEntity<Void> createActivate(@RequestBody StoreRequestDetailDto storeRequestDetailDto){
 		storeService.createActivate(storeRequestDetailDto);
 		return ResponseEntity.status(201).build();
 	}
 	
 	
 	// 파일 첨부조회
    @GetMapping("/attachments/{id}")
    public ResponseEntity<ReviewAttachmentDto> getReviewAttachment(@PathVariable("id") int id) {
        return ResponseEntity.ok(storeService.getReviewAttachment(id));
    }
    
    @GetMapping("/review/attachments/{id}/download")
    public ResponseEntity<Resource> showReviewAttachmentResource(@PathVariable("id") int id) throws IOException {
        return storeService.showReviewAttachmentResource(id);
    }
 	
 	
 	// 파일 업로드
 	@PostMapping("/api/review")
 	public ResponseEntity<Void> createReview(
 			@RequestPart("storeId") String storeId,
 			@RequestPart("userId") String userId,
 			@RequestPart("rating") String rating,
 			@RequestPart("cost") String cost,
 			@RequestPart("menu") String menu,
 			@RequestPart("review") String review,
 			@RequestPart("files") List<MultipartFile> files
 			){
 		ReviewDto reviewDto = ReviewDto.builder()
 		 		.storeId(Integer.parseInt(storeId))
 		 		.userId(userId)
 		 		.rating(Integer.parseInt(rating))
 		 		.cost(Integer.parseInt(cost))
 		 		.menu(menu)
 		 		.review(review)
 		 		.build();
 		storeService.saveReview(reviewDto, files);
 		return ResponseEntity.status(HttpStatus.CREATED).build();
 	}
 
 	
 	// 공감수 증감
 	@PatchMapping("/{reviewId}/like")
 	public ResponseEntity<ReviewRequestDto> updateReviewById(@PathVariable("reviewId") int reviewId, 
 			@RequestBody ReviewRequestDto requestDto){
 		storeService.toggleLike(reviewId, requestDto);
 		return ResponseEntity.ok().build();
 	}
 	
}
