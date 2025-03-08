package com.example.finalEclips.eclips.qna.service;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.example.finalEclips.eclips.qna.dto.AnswerUpdateDto;
import com.example.finalEclips.eclips.qna.dto.CreateQnaAnswerDto;
import com.example.finalEclips.eclips.qna.dto.CreateQnaDto;
import com.example.finalEclips.eclips.qna.dto.QnaDto;
import com.example.finalEclips.eclips.qna.dto.QnaRequestDto;

public interface QnaService {
	List<QnaDto> getQnas();
	QnaDto getQnaById(int id);
	void createQna(CreateQnaDto createQnaDto);
	void deleteQnaById(int id);
	void createQnaAnswer(CreateQnaAnswerDto createQnaAnswerDto);
	void updateQnaAnswerById(int id, AnswerUpdateDto answerUpdateDto);
	void deleteQnaAnswerById(int id);
	List<QnaDto> getSearchQna(String search);
	
	//페이지네이션
	PageImpl<QnaDto> getQnasPage(QnaRequestDto qnaRequestDto, Pageable pageable);
}
