package com.example.finalEclips.eclips.dashboard.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.dashboard.dto.IndustryGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.MonthGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.SidoGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.UserGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.YearGroupCountDto;
import com.example.finalEclips.eclips.dashboard.repository.DashboardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final DashboardMapper dashboardMapper;

    // 업종별 착한가격업소 현황
    @Override
    public List<IndustryGroupCountDto> selectIndustryStatus() {
        return dashboardMapper.selectIndustryStatus();
    }

    // 시도별 착한가격업소 현황
    @Override
    public List<SidoGroupCountDto> selectSidoStatus() {
        return dashboardMapper.selectSidoStatus();
    }

    // 년도별 착한가격업소 현황
    @Override
    public List<YearGroupCountDto> selectYearStatus() {
        return dashboardMapper.selectYearStatus();
    }

    // 월별 착한가격업소 현황
    @Override
    public List<MonthGroupCountDto> selectMonthStatus() {
        return dashboardMapper.selectMonthStatus();
    }

    // 권한별 착한가격업소 현황
    @Override
    public List<UserGroupCountDto> selectUserStatus() {
        return dashboardMapper.selectUserStatus();
    }

}
