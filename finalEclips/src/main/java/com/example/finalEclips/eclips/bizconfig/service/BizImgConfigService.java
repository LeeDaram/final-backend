package com.example.finalEclips.eclips.bizconfig.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.bizconfig.dto.CreateApprovalDto;
import com.example.finalEclips.eclips.bizconfig.dto.CreateGpbDto;

public interface BizImgConfigService {

    // 착한가격업소 등록시 테이블 2개생성
    int createGpb(CreateGpbDto gpbDto);

    void createApproval(CreateApprovalDto createApprovalDto);

    // 주방사진 업로드
    void createApprovalAttachment(int stortId, List<MultipartFile> files);

    // 재신청 : 착한가격업소 수정
    void updateGpb(CreateGpbDto gpbDto);

    // 재신청 : 승인관리목록 업데이트
    void updateApproval(CreateApprovalDto createApprovalDto);

    // 특정 사용자 ID로 STORE_ID 조회
    int selectStoreIdByUserId(String userId);

}
