package com.project.gosdaq.repository;

import com.project.gosdaq.dto.home.HomeResponseDTO;

public interface HomeRepository {
    HomeResponseDTO getStockData(String tickers);
}
