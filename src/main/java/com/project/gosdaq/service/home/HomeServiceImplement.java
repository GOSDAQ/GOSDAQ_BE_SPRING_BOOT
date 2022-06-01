package com.project.gosdaq.service.home;

import com.project.gosdaq.dto.home.HomeResponseDTO;
import com.project.gosdaq.repository.HomeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            result.put(ticker, homeRepository.getStockData(ticker));
        }

        return result;
    }
}
