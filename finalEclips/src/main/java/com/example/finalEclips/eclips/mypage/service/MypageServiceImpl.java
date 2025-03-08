package com.example.finalEclips.eclips.mypage.service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.helper.FileHelper;
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
import com.example.finalEclips.eclips.mypage.repository.MypageMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService {

    private final MypageMapper mypageMapper;
    private final FileHelper fileHelper;

    // 사용자 아이디 + 기간별로 리뷰 조회
    @Override
    public Page<ReviewDto> getReviewByPeriod(String userId, String period, Pageable pageable) {

        PaginationDto<?> paginationDto = PaginationDto.builder().userId(userId).period(period).pageable(pageable)
                .build();

        List<ReviewDto> content = mypageMapper.findReviewByPeriod(paginationDto);
        int totalCount = mypageMapper.countReviewByPeriod(userId, period);

        return new PageImpl<>(content, pageable, totalCount);
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

    // 파일 조회
    @Override
    public ReviewAttachmentDto getReviewAttachment(int id) {
        return mypageMapper.findReviewAttachmentById(id);
    }

    // 파일 불러오기
    @Override
    public ResponseEntity<Resource> downloadPostAttachmentResource(int id) throws IOException {
        ReviewAttachmentDto reviewAttachment = this.getReviewAttachment(id);
        Resource resource = fileHelper.getFileResource(reviewAttachment.getStoredFilename());

        // 파일 존재하는지 확인
        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        String contentType = Files.probeContentType(resource.getFile().toPath());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("inline; filename=\"%s\"", reviewAttachment.getOriginFilename()))
                .body(resource);

    }

}
