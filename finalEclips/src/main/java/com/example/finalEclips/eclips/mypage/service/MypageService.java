package com.example.finalEclips.eclips.mypage.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.example.finalEclips.eclips.mypage.dto.ApplyStatusDto;
import com.example.finalEclips.eclips.mypage.dto.ManagementAttachmentsDto;
import com.example.finalEclips.eclips.mypage.dto.ReservationActivateDto;
import com.example.finalEclips.eclips.mypage.dto.ReviewAttachmentDto;
import com.example.finalEclips.eclips.mypage.dto.ReviewDto;
import com.example.finalEclips.eclips.mypage.dto.StoreEditDto;
import com.example.finalEclips.eclips.mypage.dto.StoreInfoDto;
import com.example.finalEclips.eclips.mypage.dto.StoreRegisterDto;

public interface MypageService {

    // 사용자 아이디 + 기간별로 리뷰 조회
    Page<ReviewDto> getReviewByPeriod(@Param("userId") String userId, @Param("period") String period,
            Pageable pageable);

    // 리뷰 삭제
    void deleteReview(int reviewId);

    // 사용자 아이디 예약 조회 (페이지네이션 포함)
    Map<String, Object> getActivateByPeriod(String userId, String period, Pageable pageable);

    // 사용자 : 아이디 예약 조회 수
    int countActivateByPeriod(String userId, String period);

    // 예약 삭제
    void deleteActivate(int activateId);

    // 사업자 : 등록 신청 결과 조회
    ApplyStatusDto getApproval(@Param("userId") String userId);

    // 사업자 : 재신청 조회
    StoreRegisterDto getStoreRegisterInfo(@Param("userId") String userId);

    // 사업자 : 업소정보 조회
    StoreEditDto getStoreInfo(@Param("userId") String userId);

    // 사업자 : 예약 활성화 업데이트
    void saveStoreInfo(ReservationActivateDto reservationActivateDto);

    // 사업자 : 기간별 예약 조회 (페이지네이션 포함)
    Map<String, Object> getStoreActivateByPeriod(String userId, String period, Pageable pageable);

    // 사업자 : 기간별 예약 수 조회
    int getStoreActivateCountByPeriod(String userId, String period);

    // 승인관리 리스트 (페이지네이션 포함)
    Map<String, Object> getApprovalManagementList(@Param("status") String status, Pageable pageable);

    // 승인관리 리스트 수 조회
    int getTotalActivateCount(String status);

    // 승인관리 상세모달
    List<StoreInfoDto> getModalStoreInfo(@Param("storeId") int storeId);

    // 승인 반려 상태값 변경
    void updateApprovalStatus(StoreInfoDto storeInfoDto);

    // 리뷰 파일 조회
    ReviewAttachmentDto getReviewAttachment(int id);

    // 승인관리 파일 조회
    ManagementAttachmentsDto getManagement(int id);

    // 리뷰 파일 불러오기
    ResponseEntity<Resource> downloadPostAttachmentResource(int id) throws IOException;

    // 승인관리 파일 불러오기
    ResponseEntity<Resource> downloadManagementAttachmentResource(int id) throws IOException;

}
