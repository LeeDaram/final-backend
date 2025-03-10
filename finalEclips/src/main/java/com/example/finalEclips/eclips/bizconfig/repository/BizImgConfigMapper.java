package com.example.finalEclips.eclips.bizconfig.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.finalEclips.eclips.bizconfig.dto.CreateApprovalDto;
import com.example.finalEclips.eclips.bizconfig.dto.CreateGpbDto;
import com.example.finalEclips.eclips.common.dto.FileDto;

@Mapper
public interface BizImgConfigMapper {
	
	//업소생성
	void saveGpb(CreateGpbDto gpbDto);
	
	//승인관리 테이블
	void saveApproval(CreateApprovalDto approvalDto);
	
	//가게 예약여부
	void saveIsActivate(CreateApprovalDto approvalDto);
	
	//파일업로드
	void saveBizImgAttachments(@Param("storeId") int storeId , @Param("fileDtos") List<FileDto> fileDtos);
	
}
