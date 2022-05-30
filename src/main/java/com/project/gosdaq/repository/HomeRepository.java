package com.project.gosdaq.repository;

import java.util.List;

public interface HomeRepository {
    String getStockData(List<String> tickers);
}
