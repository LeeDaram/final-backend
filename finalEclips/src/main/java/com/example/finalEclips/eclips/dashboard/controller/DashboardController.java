package com.example.finalEclips.eclips.dashboard.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.dashboard.dto.IndustryGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.MonthGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.SidoGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.UserGroupCountDto;
import com.example.finalEclips.eclips.dashboard.dto.YearGroupCountDto;
import com.example.finalEclips.eclips.dashboard.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    // 업종별 착한가격업소 현황
    @GetMapping("/industry-count")
    public List<IndustryGroupCountDto> selectIndustryStatus() {
        return dashboardService.selectIndustryStatus();
    }

    // 시도별 착한가격업소 현황
    @GetMapping("/sido-count")
    public List<SidoGroupCountDto> selectSidoStatus() {
        return dashboardService.selectSidoStatus();
    }

    // 시도별 착한가격업소 현황
    @GetMapping("/year-count")
    public List<YearGroupCountDto> selectYearStatus() {
        return dashboardService.selectYearStatus();
    }

    // 월별 착한가격업소 현황
    @GetMapping("/month-count")
    public List<MonthGroupCountDto> selectMonthStatus() {
        return dashboardService.selectMonthStatus();
    }

    // 권한별 착한가격업소 현황
    @GetMapping("/user-count")
    public List<UserGroupCountDto> selectUserStatus() {
        return dashboardService.selectUserStatus();
    }

}
