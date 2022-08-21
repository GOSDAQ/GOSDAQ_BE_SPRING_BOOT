package com.project.gosdaq.service.common;

import com.project.gosdaq.dto.common.SearchRequestDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SearchServiceImplement implements SearchService{
    @Override
    public HashMap<String, Object> getStockNameFromTicker(SearchRequestDTO dto) {
        return null;
    }
}
