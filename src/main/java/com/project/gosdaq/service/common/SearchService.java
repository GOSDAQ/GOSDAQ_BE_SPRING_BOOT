package com.project.gosdaq.service.common;

import com.project.gosdaq.dto.common.Search;

import java.util.HashMap;

public interface SearchService {
    Search.ResponseDTO getStockNameFromTicker(String ticker, String region);
}
