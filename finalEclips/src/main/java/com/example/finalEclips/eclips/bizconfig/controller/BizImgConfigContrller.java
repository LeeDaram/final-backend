package com.example.finalEclips.eclips.bizconfig.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finalEclips.eclips.bizconfig.dto.CreateApprovalDto;
import com.example.finalEclips.eclips.bizconfig.dto.CreateGpbDto;
import com.example.finalEclips.eclips.bizconfig.service.BizImgConfigService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bizimg")
public class BizImgConfigContrller {
	private final BizImgConfigService bizImgConfigService;
	
	//gpb생성
		@PostMapping("/create")
		public ResponseEntity<Map<String, Integer>> createGpb(@RequestBody CreateGpbDto gpbDto) {
		    int gpbId = bizImgConfigService.createGpb(gpbDto);
		    Map<String, Integer> response = new HashMap<>();
		    response.put("storeId", gpbId); //프론트로 id값 반환해서 헤ㅠ 먼져 작성후 올라간 id값을 가져와서 승인관리 테이블만들기
		    return ResponseEntity.ok(response);
		}
		@PostMapping("/approval/")
		public ResponseEntity<CreateApprovalDto> createApproval(@RequestBody CreateApprovalDto approvalDto){
			bizImgConfigService.createApproval(approvalDto);
			return ResponseEntity.ok().build();
		}
}
