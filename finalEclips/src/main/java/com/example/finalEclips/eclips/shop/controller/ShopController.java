package com.example.finalEclips.eclips.shop.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.finalEclips.eclips.shop.dto.Shopdto;
import com.example.finalEclips.eclips.shop.service.ShopService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ShopController {
 private final ShopService shopService;
 
 @GetMapping("/shop")
 public ResponseEntity<List<Shopdto>>getAllShops(){
	 return ResponseEntity.ok(shopService.getAllShops());
 }
}

