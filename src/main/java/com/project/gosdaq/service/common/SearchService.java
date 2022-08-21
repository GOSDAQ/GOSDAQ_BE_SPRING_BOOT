package com.project.gosdaq.service.common;

import com.project.gosdaq.dto.common.SearchRequestDTO;

import java.util.HashMap;

public interface SearchService {
    HashMap<String, Object> getStockNameFromTicker(SearchRequestDTO dto);
}
