package com.example.finalEclips.eclips.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.finalEclips.eclips.shop.dto.Shopdto;
import com.example.finalEclips.eclips.shop.repository.ShopMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{
	private final ShopMapper shopMapper;
	@Override

	public List<Shopdto> getAllShops() {
		return shopMapper.findAllShops();
	}

}
