package com.project.gosdaq.service.home;

import com.project.gosdaq.dto.common.StockInfoDTO;
import com.project.gosdaq.repository.HomeRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class HomeServiceImplement implements HomeService{

    private final HomeRepository homeRepository;

    public HomeServiceImplement(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public HashMap<String, Object> getInterest(List<String> tickers) {
        HashMap<String, Object> result = new HashMap<>();

        for(String ticker : tickers){
            HashMap<String, Object> responseMap = new HashMap<>();
            StockInfoDTO stockInfoResponse = homeRepository.getStockData(ticker);

            responseMap.put("price", stockInfoResponse.getPrice());
            responseMap.put("rate", stockInfoResponse.getRate());
            responseMap.put("history", homeRepository.getHistoryData(ticker));

            result.put(ticker, responseMap);
        }

        return result;
    }
}
