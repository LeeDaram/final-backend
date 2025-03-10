package com.example.finalEclips.eclips.shop.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.finalEclips.eclips.shop.dto.Shopdto;

@Mapper
public interface ShopMapper {
  List<Shopdto> findAllShops();
}
