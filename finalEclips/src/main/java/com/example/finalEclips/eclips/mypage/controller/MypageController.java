package com.example.finalEclips.eclips.mypage.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.mypage.dto.ActivateDto;
import com.example.finalEclips.eclips.mypage.dto.ReviewDto;
import com.example.finalEclips.eclips.mypage.service.MypageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    // 사용자 아이디 + 기간별로 리뷰 조회
    @GetMapping("/review/{userId}/filter")
    public ResponseEntity<List<ReviewDto>> getReviewByPeriod(@PathVariable("userId") String userId,
            @RequestParam("period") String period) {
        List<ReviewDto> reviews = mypageService.getReviewByPeriod(userId, period);
        return ResponseEntity.ok(reviews);
    }

    // 리뷰 삭제
    @DeleteMapping("/review/delete/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") int reviewId) {
        mypageService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    // 사용자 아이디 예약 조회
    @GetMapping("/activate/{userId}/filter")
    public ResponseEntity<List<ActivateDto>> getActivateByPeriod(@PathVariable("userId") String userId,
            @RequestParam("period") String period) {
        List<ActivateDto> activates = mypageService.getActivateByPeriod(userId, period);
        return ResponseEntity.ok(activates);
    }

    // 예약 삭제
    @DeleteMapping("/activate/delete/{activateId}")
    public ResponseEntity<Void> deleteActivate(@PathVariable("activateId") int activateId) {
        mypageService.deleteActivate(activateId);
        return ResponseEntity.ok().build();
    }

}
