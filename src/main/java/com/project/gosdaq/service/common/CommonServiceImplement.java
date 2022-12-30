package com.project.gosdaq.service.common;

import com.project.gosdaq.dto.common.Exchange;
import com.project.gosdaq.dto.common.Search;
import com.project.gosdaq.dto.home.Have;
import com.project.gosdaq.repository.common.exchange.ExchangeRepository;
import com.project.gosdaq.repository.common.search.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImplement implements CommonService {

    private final SearchRepository searchRepository;
    private final ExchangeRepository exchangeRepository;

    @Autowired
    public CommonServiceImplement(SearchRepository searchRepository, ExchangeRepository exchangeRepository) {

        this.searchRepository = searchRepository;
        this.exchangeRepository = exchangeRepository;
    }

    @Override
    public Search.ResponseDTO getStockNameFromTicker(String ticker, String region) {
        List<String> tickers = new ArrayList<>();
        Search.ResponseDTO result = new Search.ResponseDTO();

        result.setCode(200);
        result.setMsg("[Spring] /common/search Success");

        if(region.equals("KR")){
            tickers.add(ticker+".KQ");
            tickers.add(ticker+".KS");
        } else {
            tickers.add(ticker);
        }

        try {
            for(String t: tickers){
                Search.ResponseDTO response = searchRepository.getStockNameFromTicker(t);

                int code = response.getCode();
                result.setCode(code);
                result.setMsg(response.getMsg());

                if(code == 200 && !(response.getData().getName().equals(""))){
                    result.setData(response.getData());
                    break;
                }
            }
        } catch (Exception e){
            result.setCode(500);
            result.setMsg("[Spring] /common/search Fail, Ckeck Spring Error Log");
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public Exchange.ResponseDTO getExchange() {
        Exchange.ResponseDTO result = new Exchange.ResponseDTO();

        try {
            result = exchangeRepository.getExchangeRate();
            result.setMsg("[Spring] /common/exchange Success");
        } catch (Exception e){
            result.setCode(500);
            result.setMsg("[Spring] /common/exchange Fail, Ckeck Spring Error Log");
            e.printStackTrace();
        }

        return result;
    }
}
