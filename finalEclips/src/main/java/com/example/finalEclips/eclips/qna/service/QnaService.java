package com.example.finalEclips.eclips.qna.service;

import java.util.List;

import com.example.finalEclips.eclips.qna.dto.CreateQnaAnswerDto;
import com.example.finalEclips.eclips.qna.dto.CreateQnaDto;
import com.example.finalEclips.eclips.qna.dto.QnaDto;

public interface QnaService {
	List<QnaDto> getQnas();
	QnaDto getQnaById(int id);
	void createQna(CreateQnaDto createQnaDto);
	void deleteQnaById(int id);
	void createQnaAnswer(CreateQnaAnswerDto createQnaAnswerDto);
}
