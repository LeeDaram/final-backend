package com.example.finalEclips.eclips.mypage.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import com.example.finalEclips.eclips.mypage.dto.ApplyStatusDto;
import com.example.finalEclips.eclips.mypage.dto.ApprovalListDto;
import com.example.finalEclips.eclips.mypage.dto.PaginationDto;
import com.example.finalEclips.eclips.mypage.dto.ReservationActivateDto;
import com.example.finalEclips.eclips.mypage.dto.ReviewAttachmentDto;
import com.example.finalEclips.eclips.mypage.dto.ReviewDto;
import com.example.finalEclips.eclips.mypage.dto.StoreActivateDto;
import com.example.finalEclips.eclips.mypage.dto.StoreEditDto;
import com.example.finalEclips.eclips.mypage.dto.StoreInfoDto;
import com.example.finalEclips.eclips.mypage.dto.StoreRegisterDto;
import com.example.finalEclips.eclips.mypage.dto.UserActivateDto;

@Mapper
public interface MypageMapper {

    // 페이지네이션된 리뷰 목록 조회
    List<ReviewDto> findReviewByPeriod(PaginationDto<?> paginationDto);

    // 전체 리뷰 개수 조회
    int countReviewByPeriod(@Param("userId") String userId, @Param("period") String period);

    // 리뷰 삭제
    void deleteReview(int reviewId);

    // 사용자 : 아이디 예약 조회 (페이지네이션 포함)
    List<UserActivateDto> findActivateByPeriod(@Param("userId") String userId, @Param("period") String period,
            @Param("pageable") Pageable pageable);

    // 사용자 : 아이디 예약 조회 수
    int countActivateByPeriod(@Param("userId") String userId, @Param("period") String period);

    // 예약 삭제
    void deleteActivate(int activateId);

    // 사업자 : 등록 신청 결과 조회
    ApplyStatusDto findApproval(@Param("userId") String userId);

    // 사업자 : 재신청 조회
    StoreRegisterDto findStoreRegisterInfo(@Param("userId") String userId);

    // 사업자 : 업소정보 조회
    StoreEditDto findStoreInfo(@Param("userId") String userId);

    // 사업자 : 예약 활성화 업데이트
    void updateStoreInfo(ReservationActivateDto reservationActivateDto);

    // 사업자 : 기간별 예약 조회 (페이지네이션 포함)
    List<StoreActivateDto> findStoreActivateByPeriod(@Param("userId") String userId, @Param("period") String period,
            @Param("pageable") Pageable pageable);

    // 사업자 : 기간별 예약 수 조회
    int countStoreActivateByPeriod(@Param("userId") String userId, @Param("period") String period);

    // 승인관리 리스트 (페이지네이션 포함)
    List<ApprovalListDto> findApprovalManagementList(@Param("status") String status,
            @Param("pageable") Pageable pageable);

    // 승인관리 리스트 수 조회
    int findTotalActivateCount(@Param("status") String status);

    // 승인관리 상세모달
    List<StoreInfoDto> findModalStoreInfo(@Param("storeId") int storeId);

    // 승인 반려 상태값 변경
    void updateApprovalStatusToPending(StoreInfoDto storeInfoDto);

    // 이미지 조회
    ReviewAttachmentDto findReviewAttachmentById(int id);

}
