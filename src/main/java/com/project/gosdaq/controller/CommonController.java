package com.project.gosdaq.controller;

import com.project.gosdaq.dto.common.Search;
import com.project.gosdaq.service.common.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController()
@RequestMapping("/common")
public class CommonController {

    private final SearchService searchService;

    @Autowired
    public CommonController(SearchService searchService){
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public Search.ResponseDTO getSearchTicker(@RequestParam("ticker") String ticker, @RequestParam(value = "region", required = false) String region){
        if(region == null){
            region = "";
        }
        return searchService.getStockNameFromTicker(ticker, region);
    }
}
