package com.project.gosdaq.repository.common.search;

import com.project.gosdaq.dto.common.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class SearchRepositoryImplement implements SearchRepository{

    @Autowired
    RestTemplate restTemplate;

        String URL = "https://gosdaq-node.herokuapp.com/stocks/";
//    String URL = "http://localhost:4000/stocks/";

    @Override
    public Search.ResponseDTO getStockNameFromTicker(String ticker) {
        Search.ResponseDTO result = new Search.ResponseDTO();

        try {
            result = restTemplate.getForObject(URL + "search/" + ticker, Search.ResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
