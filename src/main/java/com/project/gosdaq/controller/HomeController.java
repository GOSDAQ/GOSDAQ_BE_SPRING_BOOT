package com.project.gosdaq.controller;

import com.project.gosdaq.dto.StockDTO;
import com.project.gosdaq.service.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    private final HomeService homeService;

    @Autowired
    HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    @PostMapping("/interest")
    public String getInterest(@RequestBody StockDTO dto){
        List<String> tickers = dto.getTickers();
        return homeService.getInterest(tickers);
    }
}
