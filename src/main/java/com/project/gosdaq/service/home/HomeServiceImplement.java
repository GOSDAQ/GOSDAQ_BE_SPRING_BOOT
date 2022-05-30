package com.project.gosdaq.service.home;

import com.project.gosdaq.repository.HomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImplement implements HomeService{

    private final HomeRepository homeRepository;

    public HomeServiceImplement(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    @Override
    public String getInterest(List<String> tickers) {

        return homeRepository.getStockData(tickers);
    }
}
