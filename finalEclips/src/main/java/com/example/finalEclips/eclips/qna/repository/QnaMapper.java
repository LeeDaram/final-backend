package com.example.finalEclips.eclips.qna.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.finalEclips.eclips.notice.dto.CreateNoticeDto;
import com.example.finalEclips.eclips.notice.dto.NoticeDto;
import com.example.finalEclips.eclips.qna.dto.CreateQnaAnswerDto;
import com.example.finalEclips.eclips.qna.dto.CreateQnaDto;
import com.example.finalEclips.eclips.qna.dto.QnaDto;

@Mapper
public interface QnaMapper {
	List<QnaDto> findAllQnas(); //전체조회
	QnaDto findQnaById(int id); // 답변페이지 
	void incrementViewCount(int id); // 조회수 카운트
	void saveQna(CreateQnaDto createQnaDto); // 공지사항 쓰기
	void deleteQnaById(int id); //공지사항 삭제
	void saveQnaAnswer(CreateQnaAnswerDto createQnaAnswerDto);
}
