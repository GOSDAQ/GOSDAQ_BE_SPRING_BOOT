package com.project.gosdaq.controller;

import com.project.gosdaq.dto.common.SearchRequestDTO;
import com.project.gosdaq.service.common.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public HashMap<String, Object> getSearchTicker(@RequestParam SearchRequestDTO dto){
        return searchService.getStockNameFromTicker(dto);
    }
}
