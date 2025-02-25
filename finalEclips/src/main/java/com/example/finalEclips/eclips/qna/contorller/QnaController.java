package com.example.finalEclips.eclips.qna.contorller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.qna.dto.QnaDto;
import com.example.finalEclips.eclips.qna.service.QnaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/qna")
public class QnaController {
	private final QnaService qnaService;
	
	//Qna 전체조회
		@GetMapping("/main")
		public ResponseEntity<List<QnaDto>> getQnas() {
			return ResponseEntity.ok(qnaService.getQnas());
		}

}
