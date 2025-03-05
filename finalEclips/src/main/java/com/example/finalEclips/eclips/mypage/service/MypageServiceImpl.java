package com.example.finalEclips.eclips.mypage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.mypage.dto.ApplyStatusDto;
import com.example.finalEclips.eclips.mypage.dto.ApprovalListDto;
import com.example.finalEclips.eclips.mypage.dto.ReservationActivateDto;
import com.example.finalEclips.eclips.mypage.dto.ReviewDto;
import com.example.finalEclips.eclips.mypage.dto.StoreActivateDto;
import com.example.finalEclips.eclips.mypage.dto.StoreEditDto;
import com.example.finalEclips.eclips.mypage.dto.StoreInfoDto;
import com.example.finalEclips.eclips.mypage.dto.StoreRegisterDto;
import com.example.finalEclips.eclips.mypage.dto.UserActivateDto;
import com.example.finalEclips.eclips.mypage.repository.MypageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final MypageMapper mypageMapper;

    // 사용자 아이디 + 기간별로 리뷰 조회
    @Override
    public List<ReviewDto> getReviewByPeriod(String userId, String period) {
        return mypageMapper.findReviewByPeriod(userId, period);
    }

    // 리뷰 삭제
    @Override
    public void deleteReview(int reviewId) {
        mypageMapper.deleteReview(reviewId);
    }

    // 사용자 아이디 예약 조회
    @Override
    public List<UserActivateDto> getActivateByPeriod(String userId, String period) {
        return mypageMapper.findActivateByPeriod(userId, period);
    }

    // 예약 삭제
    @Override
    public void deleteActivate(int activateId) {
        mypageMapper.deleteActivate(activateId);
    }

    // 사업자 : 등록 신청 결과 조회
    @Override
    public ApplyStatusDto getApproval(String userId) {
        return mypageMapper.findApproval(userId);
    }

    // 사업자 : 재신청 조회
    @Override
    public StoreRegisterDto getStoreRegisterInfo(String userId) {
        return mypageMapper.findStoreRegisterInfo(userId);
    }

    // 사업자 : 업소정보 조회
    @Override
    public StoreEditDto getStoreInfo(String userId) {
        return mypageMapper.findStoreInfo(userId);
    }

    // 사업자 : 예약 활성화 업데이트
    @Override
    public void saveStoreInfo(ReservationActivateDto reservationActivateDto) {
        mypageMapper.updateStoreInfo(reservationActivateDto);
    }

    // 사업자 : 예약 조회
    @Override
    public List<StoreActivateDto> getStoreActivateByPeriod(String userId, String period) {
        return mypageMapper.findStoreActivateByPeriod(userId, period);
    }

    // 승인관리 리스트
    @Override
    public List<ApprovalListDto> getApprovalManagementList(String status) {
        return mypageMapper.findApprovalManagementList(status);
    }

    // 승인관리 상세모달
    @Override
    public List<StoreInfoDto> getModalStoreInfo(int storeId) {
        return mypageMapper.findModalStoreInfo(storeId);
    }

    // 승인 반려 상태값 변경
    @Override
    public void updateApprovalStatus(StoreInfoDto storeInfoDto) {
        mypageMapper.updateApprovalStatusToPending(storeInfoDto);
    }

}
