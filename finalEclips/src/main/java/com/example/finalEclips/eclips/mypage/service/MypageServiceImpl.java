package com.example.finalEclips.eclips.mypage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.mypage.dto.ActivateDto;
import com.example.finalEclips.eclips.mypage.dto.ReviewDto;
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
    public List<ActivateDto> getActivateByPeriod(String userId, String period) {
        return mypageMapper.findActivateByPeriod(userId, period);
    }

    // 예약 삭제
    @Override
    public void deleteActivate(int activateId) {
        mypageMapper.deleteActivate(activateId);
    }

}
