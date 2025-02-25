package com.example.finalEclips.eclips.qna.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.finalEclips.eclips.qna.dto.QnaDto;

@Mapper
public interface QnaMapper {
	List<QnaDto> findAllQnas();
}
