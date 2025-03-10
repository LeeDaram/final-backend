package com.example.finalEclips.eclips.bizconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.bizconfig.dto.CreateApprovalDto;
import com.example.finalEclips.eclips.bizconfig.dto.CreateGpbDto;
import com.example.finalEclips.eclips.bizconfig.repository.BizImgConfigMapper;
import com.example.finalEclips.eclips.common.dto.FileDto;
import com.example.finalEclips.eclips.helper.FileHelper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BizImgConfigServiceImpl implements BizImgConfigService {
    private final BizImgConfigMapper bizImgConfigMapper;
    private final FileHelper fileHelper;

    // 착한가격업소 등록시 테이블 2개생성
    @Override
    public int createGpb(CreateGpbDto gpbDto) {
        bizImgConfigMapper.saveGpb(gpbDto);
        return gpbDto.getStoreId();
    }

    @Override
    public void createApproval(CreateApprovalDto createApprovalDto) {
        bizImgConfigMapper.saveApproval(createApprovalDto);
        bizImgConfigMapper.saveIsActivate(createApprovalDto);
    }

    // 주방사진 업로드
    @Override
    public void createApprovalAttachment(int stortId, List<MultipartFile> files) {
        List<FileDto> fileDtos = fileHelper.uploadFiles(files);
        bizImgConfigMapper.saveBizImgAttachments(stortId, fileDtos);

    }

    // 재신청 : 착한가격업소 수정 시 테이블 2개생성..
    @Override
    public void updateGpb(CreateGpbDto gpbDto) {
        bizImgConfigMapper.updateGpb(gpbDto);
    }

    // 재신청 : 승인관리목록 업데이트
    @Override
    public void updateApproval(CreateApprovalDto createApprovalDto) {
        bizImgConfigMapper.updateApproval(createApprovalDto);
        bizImgConfigMapper.updateIsActivate(createApprovalDto);
        int storeId = createApprovalDto.getStoreId();
        bizImgConfigMapper.deleteAttachmentByStoreId(storeId);
    }

    // 특정 사용자 ID로 STORE_ID 조회
    @Override
    public int selectStoreIdByUserId(String userId) {
        return bizImgConfigMapper.selectStoreIdByUserId(userId);
    }

}
