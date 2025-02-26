package com.example.finalEclips.eclips.qna.contorller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.qna.dto.CreateQnaAnswerDto;
import com.example.finalEclips.eclips.qna.dto.CreateQnaDto;
import com.example.finalEclips.eclips.qna.dto.QnaDto;
import com.example.finalEclips.eclips.qna.service.QnaService;

import lombok.RequiredArgsConstructor;
import oracle.jdbc.proxy.annotation.Post;

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
	
	//Qna 아이디 조회
	@GetMapping("/main/{id}")
	public ResponseEntity<QnaDto> getQnaById(@PathVariable("id") int id){
		return ResponseEntity.ok(qnaService.getQnaById(id));
	}
	//Qna 작성
	@PostMapping("/create")
	public ResponseEntity<Void> createQna(@RequestBody CreateQnaDto createQnaDto){
		qnaService.createQna(createQnaDto);
		return ResponseEntity.ok().build();
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteQnaById(@PathVariable("id") int id){
		qnaService.deleteQnaById(id);
		return ResponseEntity.ok().build();
	}
	@PostMapping("/create/answer")
	ResponseEntity<Void> createQnaAnswer(@RequestBody CreateQnaAnswerDto createQnaAnswerDto){
		qnaService.createQnaAnswer(createQnaAnswerDto);
		return ResponseEntity.ok().build();
	}

}
