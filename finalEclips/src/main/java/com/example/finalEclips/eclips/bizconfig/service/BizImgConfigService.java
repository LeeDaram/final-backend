package com.example.finalEclips.eclips.bizconfig.service;

import com.example.finalEclips.eclips.bizconfig.dto.CreateApprovalDto;
import com.example.finalEclips.eclips.bizconfig.dto.CreateGpbDto;

public interface BizImgConfigService {
	int createGpb(CreateGpbDto gpbDto);
	void createApproval(CreateApprovalDto createApprovalDto);

}
