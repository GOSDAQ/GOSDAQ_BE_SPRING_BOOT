package com.project.gosdaq.repository.common;

import com.project.gosdaq.dto.common.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class SearchRepositoryImplement implements SearchRepository{

    @Autowired
    RestTemplate restTemplate;

    //    String URL = "https://gosdaq-node.herokuapp.com/stocks/";
    String URL = "http://localhost:4000/stocks/";

    @Override
    public Search.ResponseDTO getStockNameFromTicker(String ticker) {
        return restTemplate.getForObject(URL + "search/" + ticker, Search.ResponseDTO.class);
    }
}
