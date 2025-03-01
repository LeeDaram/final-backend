package com.example.finalEclips.eclips.mypage.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.finalEclips.eclips.mypage.dto.ActivateDto;
import com.example.finalEclips.eclips.mypage.dto.ReviewDto;

@Mapper
public interface MypageMapper {

    // 사용자 아이디 + 기간별로 리뷰 조회
    List<ReviewDto> findReviewByPeriod(@Param("userId") String userId, @Param("period") String period);

    // 리뷰 삭제
    void deleteReview(int reviewId);

    // 사용자 아이디 예약 조회
    List<ActivateDto> findActivateByPeriod(@Param("userId") String userId, @Param("period") String period);

    // 예약 삭제
    void deleteActivate(int activateId);

}
