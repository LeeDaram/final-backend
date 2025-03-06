package com.example.finalEclips.eclips.dashboard.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.finalEclips.eclips.dashboard.dto.IndustryGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.MonthGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.SidoGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.UserGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.YearGroupCountDto;

@Mapper
public interface DashboardMapper {

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
