package com.project.gosdaq.service.home;

import com.project.gosdaq.dto.home.MyStockRequestDTO;

import java.util.HashMap;
import java.util.List;

public interface HomeService {
    HashMap<String, Object> getInterest(List<String> tickers);
    HashMap<String, Object> getMyStock(MyStockRequestDTO tickers);
}

