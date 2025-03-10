package com.example.finalEclips.eclips.bizconfig.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.bizconfig.dto.CreateApprovalDto;
import com.example.finalEclips.eclips.bizconfig.dto.CreateGpbDto;

public interface BizImgConfigService {
	//착한가격업소 등록시 테이블 2개생성
	int createGpb(CreateGpbDto gpbDto);
	void createApproval(CreateApprovalDto createApprovalDto);
	
	//주방사진 업로드
	void createApprovalAttachment(int stortId, List<MultipartFile> files);
	

}
