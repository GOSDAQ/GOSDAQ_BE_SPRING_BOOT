package com.project.gosdaq.repository;

import com.project.gosdaq.dto.home.HomeResponseDTO;

import java.util.List;

public interface HomeRepository {
    HomeResponseDTO getStockData(String tickers);
}
