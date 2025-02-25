package com.example.finalEclips.eclips.qna.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

}
