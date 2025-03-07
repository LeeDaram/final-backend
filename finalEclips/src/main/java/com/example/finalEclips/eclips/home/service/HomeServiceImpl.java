package com.example.finalEclips.eclips.home.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.home.dto.HomeCountDto;
import com.example.finalEclips.eclips.home.dto.HomeNoticeDto;
import com.example.finalEclips.eclips.home.dto.LikeCountTopDto;
import com.example.finalEclips.eclips.home.repository.HomeMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HomeService{
	private final HomeMapper homeMapper;
	
	@Override
	public List<LikeCountTopDto> getAllTodayShop() {
		return homeMapper.findAllLikeCount();
	}

	@Override
	public List<HomeCountDto> getAllTotalCount() {
		return homeMapper.findAllMainCount();
	}

	@Override
	public List<HomeNoticeDto> getAllNotices() {
		return homeMapper.findAllMainNotice();
	}

}
