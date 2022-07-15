package com.project.gosdaq.service.home;

import com.project.gosdaq.dto.common.HistoryDTO;
import com.project.gosdaq.dto.common.StockInfoDTO;
import com.project.gosdaq.dto.home.MyStockRequestDTO;
import com.project.gosdaq.repository.HomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HomeServiceImplement implements HomeService{

    private final HomeRepository homeRepository;

    @Autowired
    public HomeServiceImplement(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public HashMap<String, Object> getInterest(List<String> tickers) {

        boolean isError = false;
        String message = "[Spring] /home/interest Success";

        HashMap<String, Object> result = new HashMap<>();
        List<HashMap<String, Object>> data = new ArrayList<>();

        for(String ticker : tickers){
            HashMap<String, Object> responseMap = new HashMap<>();


            StockInfoDTO stockInfoResponse = homeRepository.getStockData(ticker);
            HistoryDTO historyInfoResponse = homeRepository.getHistoryData(ticker);

            if(stockInfoResponse==null || historyInfoResponse==null){
                isError = true;
                message = "[Spring] /home/have Fail, Check Ticker is Correct : " + ticker;
                break;
            }

            responseMap.put("id", ticker);
            responseMap.put("price", stockInfoResponse.getPrice());
            responseMap.put("rate", stockInfoResponse.getRate());
            responseMap.put("history", historyInfoResponse);

            data.add(responseMap);
        }

        result.put("isError", isError);
        result.put("message", message);

        if(!isError){
            result.put("data", data);
        }

        return result;
    }

    @Override
    public HashMap<String, Object> getMyStock(MyStockRequestDTO dto) {

        boolean isError = false;
        String message = "[Spring] /home/have Success";

        List<MyStockRequestDTO.StockInfo> stockList = dto.getData();
        DecimalFormat formatter = new DecimalFormat("###,###");
        double exchange = homeRepository.getExchangeData();

        HashMap<String, Object> result = new HashMap<>();
        result.put("exchange", exchange);

        List<HashMap<String, Object>> data = new ArrayList<>();

        for(MyStockRequestDTO.StockInfo stock : stockList){
            HashMap<String, Object> stockData = new HashMap<>();

            StockInfoDTO stockInfoResponse = homeRepository.getStockData(stock.ticker);

            if(stockInfoResponse==null){
                isError = true;
                message = "[Spring] /home/interest Fail, Check Ticker is Correct : " + stock.ticker;
                break;
            }

            double income = Math.round((stockInfoResponse.getPrice() - stock.avg) * stock.amt * exchange);

            stockData.put(stock.ticker, formatter.format(income));

            data.add(stockData);
        }

        result.put("isError", isError);
        result.put("message", message);

        if(!isError){
            result.put("data", data);
        }

        return result;
    }
}
