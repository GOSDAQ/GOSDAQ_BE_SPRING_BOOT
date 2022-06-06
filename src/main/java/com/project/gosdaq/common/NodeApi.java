package com.project.gosdaq.common;

import com.project.gosdaq.dto.common.HistoryDTO;
import com.project.gosdaq.dto.common.StockInfoDTO;
import org.springframework.web.client.RestTemplate;

public class NodeApi {
    String URL = "http://localhost:4000/stocks/";

    public StockInfoDTO getStockInfo(String path) throws Exception{
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(URL + path, StockInfoDTO.class);
    }

    public HistoryDTO getStockHistory(String path, int period) throws Exception{
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(URL + path + "/" + period, HistoryDTO.class);
    }
}
