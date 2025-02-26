package com.example.finalEclips.eclips.qna.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.qna.dto.CreateQnaAnswerDto;
import com.example.finalEclips.eclips.qna.dto.CreateQnaDto;
import com.example.finalEclips.eclips.qna.dto.QnaDto;
import com.example.finalEclips.eclips.qna.repository.QnaMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QnaServiceImpl implements QnaService{
	private final QnaMapper qnaMapper;

	@Override
	public List<QnaDto> getQnas() {
		return qnaMapper.findAllQnas();
	}

	@Override
	public QnaDto getQnaById(int id) {
		qnaMapper.incrementViewCount(id);
		return qnaMapper.findQnaById(id);
	}

	@Override
	public void createQna(CreateQnaDto createQnaDto) {
		//회원getId있어야함
		qnaMapper.saveQna(createQnaDto);
	}

	@Override
	public void deleteQnaById(int id) {
		qnaMapper.deleteQnaById(id);
	}

	@Override
	public void createQnaAnswer(CreateQnaAnswerDto createQnaAnswerDto) {
		qnaMapper.saveQnaAnswer(createQnaAnswerDto);
	}

}
