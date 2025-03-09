package com.example.finalEclips.eclips.bizconfig.service;

import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.bizconfig.dto.CreateApprovalDto;
import com.example.finalEclips.eclips.bizconfig.dto.CreateGpbDto;
import com.example.finalEclips.eclips.bizconfig.repository.BizImgConfigMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BizImgConfigServiceImpl implements BizImgConfigService{
	private final BizImgConfigMapper bizImgConfigMapper;
	
	@Override
	public int createGpb(CreateGpbDto gpbDto) {
		bizImgConfigMapper.saveGpb(gpbDto);
		return gpbDto.getStoreId();
	}

	@Override
	public void createApproval(CreateApprovalDto createApprovalDto) {
		bizImgConfigMapper.saveApproval(createApprovalDto);
	}
	

}
