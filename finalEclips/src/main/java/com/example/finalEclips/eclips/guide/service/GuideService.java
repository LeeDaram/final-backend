package com.example.finalEclips.eclips.guide.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.guide.dto.Guide;
import com.example.finalEclips.eclips.guide.mapper.GuideMapper;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GuideService {
    private final GuideMapper guideMapper;
    
    public List<Guide> getAllGuides() {
        return guideMapper.getAllGuides();
    }


}
