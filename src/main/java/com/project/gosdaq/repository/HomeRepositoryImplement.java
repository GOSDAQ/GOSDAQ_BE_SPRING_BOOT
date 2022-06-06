package com.project.gosdaq.repository;

import com.project.gosdaq.common.NodeApi;
import com.project.gosdaq.dto.common.HistoryDTO;
import com.project.gosdaq.dto.common.StockInfoDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HomeRepositoryImplement implements HomeRepository {
    @Override
    public StockInfoDTO getStockData(String ticker) {

        NodeApi nodeApi = new NodeApi();
        StockInfoDTO result = new StockInfoDTO();

        try {
            result =  nodeApi.getStockInfo("interest/" + ticker);
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }

    public HistoryDTO getHistoryData(String ticker) {

        NodeApi nodeApi = new NodeApi();
        HistoryDTO result = new HistoryDTO();

        try {
            result =  nodeApi.getStockHistory("history/" + ticker, 30);
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }
}