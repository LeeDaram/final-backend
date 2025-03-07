package com.example.finalEclips.eclips.home.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.finalEclips.eclips.home.dto.HomeCountDto;
import com.example.finalEclips.eclips.home.dto.HomeNoticeDto;
import com.example.finalEclips.eclips.home.dto.LikeCountTopDto;

@Mapper
public interface HomeMapper {
	List<LikeCountTopDto> findAllLikeCount();
	List<HomeCountDto> findAllMainCount();
	List<HomeNoticeDto> findAllMainNotice();
}
