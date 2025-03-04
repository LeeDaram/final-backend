package com.example.finalEclips.eclips.faq.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.faq.dto.FaqDto;
import com.example.finalEclips.eclips.faq.repository.FaqMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FaqServiceImpl implements FaqService{
	private final FaqMapper faqMapper;

	@Override
	public List<FaqDto> getAllFaqs() {
		return faqMapper.findAllFaqs();
	}

}
