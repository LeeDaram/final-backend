package com.example.finalEclips.eclips.store.repository;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.finalEclips.eclips.common.dto.FileDto;
import com.example.finalEclips.eclips.common.dto.PaginationDto;
import com.example.finalEclips.eclips.store.dto.IndustryDto;
import com.example.finalEclips.eclips.store.dto.ReviewAttachmentDto;
import com.example.finalEclips.eclips.store.dto.ReviewDto;
import com.example.finalEclips.eclips.store.dto.ReviewRequestDto;
import com.example.finalEclips.eclips.store.dto.SidoDto;
import com.example.finalEclips.eclips.store.dto.SigunguDto;
import com.example.finalEclips.eclips.store.dto.StoreAddressDto;
import com.example.finalEclips.eclips.store.dto.StoreDto;
import com.example.finalEclips.eclips.store.dto.StoreFilterDto;
import com.example.finalEclips.eclips.store.dto.StoreRequestDetailDto;
import com.example.finalEclips.eclips.store.dto.StoreReservationDto;
import com.example.finalEclips.eclips.store.dto.StoreReviewDto;
import com.example.finalEclips.eclips.store.dto.UserDto;

@Mapper
public interface StoreMapper {
    List<StoreDto> findPaginatedStores(PaginationDto<?> paginationDto); // 페이지 쪼개기
    int findPaginatedStoresCount(PaginationDto<?> paginationDto); // 총 페이지 카운트
    List<SidoDto> findSidoName(); // 시도
    List<SigunguDto> findSigunguName(); // 시군구
    List<IndustryDto> findIndustryName(); // 업종명
    List<StoreFilterDto> filterStore(PaginationDto<?> paginationDto); // 필터 페이지 쪼개기
    int filterStoreCount(PaginationDto<?> paginationDto); // 필터 총 페이지 카운트
    List<StoreAddressDto> findAllAddress(); // 주소
    List<StoreReservationDto> findAllStoreDetail(); // 업소상세페이지
    void saveActivate(StoreRequestDetailDto storeRequestDetailDto); // 업소상페이지 예약
    UserDto findUserById(String userId); // 유저 조회
    boolean existsByUserIdAndDate(@Param("userId") String userId, @Param("reservationDate") LocalDate reservationDate); // 예약 중복확인
    List<ReviewDto> findAllReviews(); // 모든리뷰조회
    void saveReview(ReviewDto reviewDto); // 리뷰 저장
    void saveReviewAttechments(@Param("reviewId") int reviewId, @Param("fileDtos") List<FileDto> fileDtos); // 이용후기 파일 업로드
    List<ReviewAttachmentDto> findAttachmentByReviewId(int reviewId); // 리뷰전체조회
    ReviewAttachmentDto findReviewAttachmentById(int id); //리뷰 아이디 조회
    List<StoreReviewDto> findStoreReviews(PaginationDto<?> paginationDto);
    int findStoreReviewTotalCount(PaginationDto<?> paginationDto);
    void incrementLikeCount(ReviewRequestDto reviewRequestDto); // 공감수 증감
}
