package com.project.gosdaq.service.home;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImplement implements HomeService{

    @Override
    public String getInterest(List<String> tickers) {

        for(String ticker : tickers){
            System.out.println(ticker);
        }

        return tickers.get(0);
    }
}
