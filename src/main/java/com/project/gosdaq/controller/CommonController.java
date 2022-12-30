package com.project.gosdaq.controller;

import com.project.gosdaq.dto.common.Exchange;
import com.project.gosdaq.dto.common.Search;
import com.project.gosdaq.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/common")
public class CommonController {

    private final CommonService commonService;

    @Autowired
    public CommonController(CommonService commonService){
        this.commonService = commonService;
    }

    @GetMapping("/search")
    public Search.ResponseDTO getSearchTicker(@RequestParam("ticker") String ticker, @RequestParam(value = "region", required = false) String region){
        if(region == null){
            region = "";
        }
        return commonService.getStockNameFromTicker(ticker, region);
    }

    @GetMapping("/exchange")
    public Exchange.ResponseDTO getExchange(){
        return commonService.getExchange();
    }
}
