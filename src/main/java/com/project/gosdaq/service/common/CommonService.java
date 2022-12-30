package com.project.gosdaq.service.common;

import com.project.gosdaq.dto.common.Exchange;
import com.project.gosdaq.dto.common.Search;

public interface CommonService {
    Search.ResponseDTO getStockNameFromTicker(String ticker, String region);
    Exchange.ResponseDTO getExchange();
}
