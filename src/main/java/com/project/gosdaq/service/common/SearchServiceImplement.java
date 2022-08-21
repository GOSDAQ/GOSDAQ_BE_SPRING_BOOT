package com.project.gosdaq.service.common;

import com.project.gosdaq.dto.common.Search;
import com.project.gosdaq.repository.common.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SearchServiceImplement implements SearchService{

    private final SearchRepository searchRepository;

    @Autowired
    public SearchServiceImplement(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public Search.ResponseDTO getStockNameFromTicker(String ticker, String region) {
        List<String> tickers = new ArrayList<>();
        Search.ResponseDTO result = new Search.ResponseDTO();

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

                if(code == 200 && !(response.getData().getName().equals(""))){
                    result.setData(response.getData());
                    result.setMsg(response.getMsg());
                    result.setCode(response.getCode());
                    break;
                } else if(code != 200) {
                    result.setMsg(response.getMsg());
                    result.setCode(response.getCode());
                }
            }
        } catch (Exception e){
            result.setMsg("Search Ticker Failed... See Spring Error Log");
        }

        return result;
    }
}
