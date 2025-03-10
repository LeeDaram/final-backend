package com.example.finalEclips.eclips.guide.mapper;
import org.apache.ibatis.annotations.*;

import com.example.finalEclips.eclips.guide.dto.Guide;

import java.util.List;
@Mapper
public interface GuideMapper {

	    List<Guide> getAllGuides();
	}


