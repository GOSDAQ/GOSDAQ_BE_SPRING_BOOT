package com.project.gosdaq.controller;

import com.project.gosdaq.dto.home.InterestRequestDTO;
import com.project.gosdaq.dto.home.MyStockRequestDTO;
import com.project.gosdaq.service.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    @PostMapping("/interest")
    public HashMap<String, Object> getInterest(@RequestBody InterestRequestDTO dto){
        List<String> tickers = dto.getTickers();
        return homeService.getInterest(tickers);
    }

    @PostMapping("/test")
    public HashMap<String, Object> getMyStockInfo(@RequestBody MyStockRequestDTO dto){
        return homeService.getMyStock(dto);
    }
}
