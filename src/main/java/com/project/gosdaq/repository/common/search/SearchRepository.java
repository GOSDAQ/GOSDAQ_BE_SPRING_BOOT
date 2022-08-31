package com.project.gosdaq.repository.common.search;

import com.project.gosdaq.dto.common.Search;

public interface SearchRepository {
    Search.ResponseDTO getStockNameFromTicker(String ticker);
}
