package com.project.gosdaq.repository.common;

import com.project.gosdaq.dto.common.Search;

public interface SearchRepository {
    Search.ResponseDTO getStockNameFromTicker(String ticker);
}
