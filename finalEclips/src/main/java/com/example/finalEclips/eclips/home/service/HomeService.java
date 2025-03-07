package com.example.finalEclips.eclips.home.service;

import java.util.List;

import com.example.finalEclips.eclips.home.dto.HomeCountDto;
import com.example.finalEclips.eclips.home.dto.HomeNoticeDto;
import com.example.finalEclips.eclips.home.dto.LikeCountTopDto;

public interface HomeService {
	List<LikeCountTopDto> getAllTodayShop();
	List<HomeCountDto> getAllTotalCount();
	List<HomeNoticeDto> getAllNotices();

}
