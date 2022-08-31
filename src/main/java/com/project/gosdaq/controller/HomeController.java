package com.project.gosdaq.controller;

import com.project.gosdaq.dto.home.Have;
import com.project.gosdaq.dto.home.Interest;
import com.project.gosdaq.service.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/home")
public class HomeController {

    private final HomeService homeService;

    @Autowired
    public HomeController(HomeService homeService){
        this.homeService = homeService;
    }

    @PostMapping("/interest")
    public Interest.ResponseDTO getInterest(@RequestBody Interest.RequestDTO dto){
        return homeService.getInterest(dto.getTickers());
    }

    @PostMapping("/have")
    public Have.ResponseDTO getMyStockInfo(@RequestBody Have.RequestDTO dto){
        return homeService.getHave(dto);
    }
}
