package com.project.gosdaq.repository;

import com.project.gosdaq.dto.common.HistoryDTO;
import com.project.gosdaq.dto.common.StockInfoDTO;

public interface HomeRepository {
    StockInfoDTO getStockData(String tickers);
    HistoryDTO getHistoryData(String tickers);
    double getExchangeData();
}

