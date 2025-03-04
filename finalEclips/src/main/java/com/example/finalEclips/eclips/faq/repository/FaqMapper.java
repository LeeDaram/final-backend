package com.example.finalEclips.eclips.faq.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.finalEclips.eclips.faq.dto.FaqDto;

@Mapper
public interface FaqMapper {
	List<FaqDto> findAllFaqs();

}
