package com.example.finalEclips.eclips.averageprice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.averageprice.dto.AveragePriceDto;
import com.example.finalEclips.eclips.averageprice.dto.LocationDto;
import com.example.finalEclips.eclips.averageprice.service.AveragePriceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AveragePriceController {

    private final AveragePriceService averagePriceService;

    // 평균가격 조회
    @GetMapping("/approval")
    public ResponseEntity<?> getAveragePrice(@RequestParam("mainMenu") String mainMenu,
            @RequestParam("sidoName") String sidoName) {
        AveragePriceDto request = new AveragePriceDto();
        request.setMainMenu(mainMenu);
        request.setSidoName(sidoName);
        Integer avgPrice = averagePriceService.getAveragePrice(request);
        if (avgPrice == null) {
            return ResponseEntity.ok("No data found for the given criteria.");
        }
        request.setAvgPrice(avgPrice);
        return ResponseEntity.ok(request);
    }

    // 사용자 지역 조회
    @GetMapping("/location/{userId}")
    public ResponseEntity<LocationDto> getLocation(@PathVariable("userId") String userId) {
        LocationDto location = averagePriceService.getLocationByUserId(userId);
        if (location != null) {
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
