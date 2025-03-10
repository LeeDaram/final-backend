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
public class BizImgConfigServiceImpl implements BizImgConfigService{
	private final BizImgConfigMapper bizImgConfigMapper;
	private final FileHelper fileHelper;
	
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

	@Override
	public void createApprovalAttachment(int stortId, List<MultipartFile> files) {
		List<FileDto> fileDtos = fileHelper.uploadFiles(files);
		bizImgConfigMapper.saveBizImgAttachments(stortId, fileDtos);
        
		
	}
	

}
