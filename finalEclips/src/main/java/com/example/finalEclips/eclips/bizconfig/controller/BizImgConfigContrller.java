package com.example.finalEclips.eclips.bizconfig.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.example.finalEclips.eclips.bizconfig.dto.CreateApprovalDto;
import com.example.finalEclips.eclips.bizconfig.dto.CreateGpbDto;
import com.example.finalEclips.eclips.bizconfig.service.BizImgConfigService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bizimg")
public class BizImgConfigContrller {

    private final BizImgConfigService bizImgConfigService;

    // gpb생성
    @PostMapping("/create")
    public ResponseEntity<Map<String, Integer>> createGpb(@RequestBody CreateGpbDto gpbDto) {
        int gpbId = bizImgConfigService.createGpb(gpbDto);
        Map<String, Integer> response = new HashMap<>();
        response.put("storeId", gpbId); // 프론트로 id값 반환해서 헤ㅠ 먼져 작성후 올라간 id값을 가져와서 승인관리 테이블만들기
        return ResponseEntity.ok(response);
    }

    @PostMapping("/approval")
    public ResponseEntity<CreateApprovalDto> createApproval(@RequestBody CreateApprovalDto approvalDto) {
        bizImgConfigService.createApproval(approvalDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/approval/attachment")
    public ResponseEntity<Void> createApprovalAttachment(@RequestPart("files") List<MultipartFile> files,
            @RequestParam("storeId") int storeId) {
        bizImgConfigService.createApprovalAttachment(storeId, files);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // 재신청 : 착한가격업소 수정 시 테이블 2개생성
    @PutMapping("/update")
    public ResponseEntity<Map<String, Integer>> updateGpb(@RequestBody CreateGpbDto gpbDto) {
        bizImgConfigService.updateGpb(gpbDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 재신청 : 승인관리목록 업데이트
    @PutMapping("/update/approval")
    public ResponseEntity<CreateApprovalDto> updateApproval(@RequestBody CreateApprovalDto approvalDto) {
        bizImgConfigService.updateApproval(approvalDto);
        return ResponseEntity.ok().build();
    }

    // 특정 사용자 ID로 STORE_ID 조회
    @GetMapping("/storeId")
    public ResponseEntity<Integer> getStoreIdByUserId(@RequestParam("userId") String userId) {
        int storeId = bizImgConfigService.selectStoreIdByUserId(userId);
        return ResponseEntity.ok(storeId);
    }

}
