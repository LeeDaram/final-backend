package com.example.finalEclips.eclips.store.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.store.dto.FilterRequestDto;
import com.example.finalEclips.eclips.store.dto.IndustryDto;
import com.example.finalEclips.eclips.store.dto.ReviewAttachmentDto;
import com.example.finalEclips.eclips.store.dto.SidoDto;
import com.example.finalEclips.eclips.store.dto.SigunguDto;
import com.example.finalEclips.eclips.store.dto.StoreAddressDto;
import com.example.finalEclips.eclips.store.dto.StoreReservationDto;
import com.example.finalEclips.eclips.store.dto.StoreReviewDto;
import com.example.finalEclips.eclips.store.dto.StoreReviewRequestDto;
import com.example.finalEclips.eclips.store.dto.UserDto;
import com.example.finalEclips.eclips.store.dto.StoreDto;
import com.example.finalEclips.eclips.store.dto.StoreFilterDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDetailDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDto;
import com.example.finalEclips.eclips.store.dto.ReviewDto;

public interface StoreService {
    PageImpl<StoreDto> getStores(StoreRequestDto storeRequestDto, Pageable pageable); // 착한가격업소 모든 정보 조회
    List<SidoDto> getSido(); // 시도 데이터
    List<SigunguDto> getSigungu(); // 시군구 데이터
    List<IndustryDto> getIndustry(); // 업종명 데이터
    PageImpl<StoreFilterDto> getStoreFilter(FilterRequestDto filterRequestDto, Pageable pageable); // 필터 업소
    List<StoreAddressDto> getAllAddress(); // 주소
    List<StoreReservationDto> getAllStoreDetail(); // 업소 상세 페이지
    void createActivate(StoreRequestDetailDto storeRequestDetailDto); //업소상세페이지 예약 
    UserDto getUser(String userId); // 유저 조회
    void saveReview(ReviewDto reviewDto, List<MultipartFile> files); // 리뷰 저장
    ReviewAttachmentDto getReviewAttachment(int id);
    ResponseEntity<Resource> showReviewAttachmentResource(int id) throws IOException; // 파일 하나씩 조회
    List<ReviewAttachmentDto> getReviewAttachmentsByReviewId(int reviewId); // 리뷰 아이디로 다 조회
    PageImpl<StoreReviewDto> getStoreReviews(StoreReviewRequestDto requestDto, Pageable pageable);
}
