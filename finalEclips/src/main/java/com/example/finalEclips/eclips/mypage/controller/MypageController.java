package com.example.finalEclips.eclips.mypage.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.mypage.dto.ApplyStatusDto;
import com.example.finalEclips.eclips.mypage.dto.ReservationActivateDto;
import com.example.finalEclips.eclips.mypage.dto.ReviewAttachmentDto;
import com.example.finalEclips.eclips.mypage.dto.ReviewDto;
import com.example.finalEclips.eclips.mypage.dto.StoreEditDto;
import com.example.finalEclips.eclips.mypage.dto.StoreInfoDto;
import com.example.finalEclips.eclips.mypage.dto.StoreRegisterDto;
import com.example.finalEclips.eclips.mypage.service.MypageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    // 사용자 아이디 + 기간별로 리뷰 조회
    @GetMapping("/review/{userId}/filter/{period}")
    public Page<ReviewDto> getUserReviewsByPeriod(@PathVariable("userId") String userId,
            @PathVariable("period") String period, Pageable pageable) {
        return mypageService.getReviewByPeriod(userId, period, pageable);
    }

    // 리뷰 삭제
    @DeleteMapping("/review/delete/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") int reviewId) {
        mypageService.deleteReview(reviewId);
        return ResponseEntity.ok().build();
    }

    // 사용자 아이디 예약 조회 (페이지네이션 포함)
    @GetMapping("/activate/{userId}/filter")
    public ResponseEntity<?> getActivateByPeriod(@PathVariable("userId") String userId,
            @RequestParam("period") String period, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> response = mypageService.getActivateByPeriod(userId, period, pageable);
        return ResponseEntity.ok(response);
    }

    // 예약 삭제
    @DeleteMapping("/activate/delete/{activateId}")
    public ResponseEntity<Void> deleteActivate(@PathVariable("activateId") int activateId) {
        mypageService.deleteActivate(activateId);
        return ResponseEntity.ok().build();
    }

    // 사업자 : 등록 신청 결과 조회
    @GetMapping("/approval/result/{userId}")
    public ResponseEntity<ApplyStatusDto> getApproval(@PathVariable("userId") String userId) {
        ApplyStatusDto result = mypageService.getApproval(userId);
        return ResponseEntity.ok(result);
    }

    // 사업자 : 재신청 조회
    @GetMapping("/register/storeInfo/{userId}")
    public ResponseEntity<StoreRegisterDto> getStoreRegisterInfo(@PathVariable("userId") String userId) {
        StoreRegisterDto result = mypageService.getStoreRegisterInfo(userId);
        return ResponseEntity.ok(result);
    }

    // 사업자 : 업소정보 조회
    @GetMapping("/storeInfo/{userId}")
    public ResponseEntity<StoreEditDto> getStoreInfo(@PathVariable("userId") String userId) {
        StoreEditDto result = mypageService.getStoreInfo(userId);
        return ResponseEntity.ok(result);
    }

    // 사업자 : 예약변경
    @PutMapping("/update/store/activation")
    public ResponseEntity<String> updateStoreInfo(@RequestBody ReservationActivateDto reservationActivateDto) {
        try {
            mypageService.saveStoreInfo(reservationActivateDto);
            return ResponseEntity.ok("Store reservation activation updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating store reservation activation.");
        }
    }

    // 사업자 아이디 + 기간별로 예약현황 조회
    @GetMapping("/store/activate/{userId}/filter")
    public ResponseEntity<?> getStoreActivateByPeriod(@PathVariable("userId") String userId,
            @RequestParam("period") String period, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> response = mypageService.getStoreActivateByPeriod(userId, period, pageable);
        return ResponseEntity.ok(response);
    }

    // 승인관리 리스트
    @GetMapping("/approva/management/list")
    public ResponseEntity<?> getApprovalManagementList(@RequestParam("status") String status,
            @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Map<String, Object> response = mypageService.getApprovalManagementList(status, pageable);
        return ResponseEntity.ok(response);
    }

    // 승인관리 상세모달
    @GetMapping("/approvaList/modal/{storeId}")
    public ResponseEntity<List<StoreInfoDto>> getStoreInfo(@PathVariable("storeId") int storeId) {
        List<StoreInfoDto> storeInfo = mypageService.getModalStoreInfo(storeId);
        return ResponseEntity.ok(storeInfo);
    }

    // 승인 반려 상태값 변경
    @PutMapping("/approvaList/modal/update-status")
    public String updateApprovalStatus(@RequestBody StoreInfoDto storeInfoDto) {
        try {
            mypageService.updateApprovalStatus(storeInfoDto);
            return "승인 상태가 업데이트 되었습니다.";
        } catch (Exception e) {
            return "업데이트에 실패했습니다: " + e.getMessage();
        }
    }

    // 파일 조회
    @GetMapping("/attachments/{id}")
    public ResponseEntity<ReviewAttachmentDto> getReviewAttachment(@PathVariable("id") int id) {
        return ResponseEntity.ok(mypageService.getReviewAttachment(id));
    }

    // 파일 불러오기
    @GetMapping("/posts/attachments/{id}")
    public ResponseEntity<Resource> getPostAttachment(@PathVariable("id") int id) throws IOException {
        return mypageService.downloadPostAttachmentResource(id);
    }

}
