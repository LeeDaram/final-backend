package com.example.finalEclips.eclips.qna.service;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.finalEclips.eclips.common.dto.PaginationDto;
import com.example.finalEclips.eclips.qna.dto.AnswerUpdateDto;
import com.example.finalEclips.eclips.qna.dto.CreateQnaAnswerDto;
import com.example.finalEclips.eclips.qna.dto.CreateQnaDto;
import com.example.finalEclips.eclips.qna.dto.QnaDto;
import com.example.finalEclips.eclips.qna.dto.QnaRequestDto;
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

	@Transactional
	@Override
	public void deleteQnaById(int id) {
//		qnaMapper.deleteQnaAnswerById(id); 모든답글삭제
		qnaMapper.deleteAnswersByQuestionId(id); //모든답글 삭제
		qnaMapper.deleteQnaById(id);
	}

	@Override
	public void createQnaAnswer(CreateQnaAnswerDto createQnaAnswerDto) {
		qnaMapper.saveQnaAnswer(createQnaAnswerDto);
	}

	@Override
	public void updateQnaAnswerById(int id, AnswerUpdateDto answerUpdateDto) {
		answerUpdateDto.setAnswerId(id);
		qnaMapper.updateQnaAnwerById(answerUpdateDto);
	}

	@Override
	public void deleteQnaAnswerById(int id) {
		qnaMapper.deleteQnaAnswerById(id);
		
	}

	@Override
	public List<QnaDto> getSearchQna(String search) {
		return qnaMapper.findSearchQna(search);
	}

	@Override
	public PageImpl<QnaDto> getQnasPage(QnaRequestDto qnaRequestDto, Pageable pageable) {
		PaginationDto<?> paginationDto = PaginationDto.builder().data(qnaRequestDto).pageable(pageable).build();
		List<QnaDto> content = qnaMapper.findPaginatedQnas(paginationDto);
		int totalCount = qnaMapper.findPaginatedQnasCount(paginationDto);
		return new PageImpl<>(content, pageable, totalCount);
	}

}
