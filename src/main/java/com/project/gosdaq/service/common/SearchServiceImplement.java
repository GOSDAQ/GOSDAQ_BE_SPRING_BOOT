package com.project.gosdaq.service.common;

import com.project.gosdaq.dto.common.Search;
import com.project.gosdaq.repository.common.search.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

                if(code == 200 && !(response.getData().getName().equals(""))){
                    result.setData(response.getData());
                    break;
                } else if(code == 500) {
                    result.setCode(response.getCode());
                    result.setMsg(response.getMsg());
                }
            }
        } catch (Exception e){
            result.setCode(500);
            result.setMsg("[Spring] /common/search Fail, Ckeck Spring Error Log");
            e.printStackTrace();
        }

        return result;
    }
}
