package com.example.finalEclips.eclips.dashboard.service;

import java.util.List;

import com.example.finalEclips.eclips.dashboard.dto.IndustryGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.MonthGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.SidoGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.UserGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.YearGroupCountDto;

public interface DashboardService {

    // 업종별 착한가격업소 현황
    List<IndustryGroupCountDto> selectIndustryStatus();

    // 시도별 착한가격업소 현황
    List<SidoGroupCountDto> selectSidoStatus();

    // 년도별 착한가격업소 현황
    List<YearGroupCountDto> selectYearStatus();

    // 월별 착한가격업소 현황
    List<MonthGroupCountDto> selectMonthStatus();

    // 권한별 착한가격업소 현황
    List<UserGroupCountDto> selectUserStatus();

}
