package com.project.gosdaq.repository.common.search;

import com.project.gosdaq.dto.common.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class SearchRepositoryImplement implements SearchRepository{

    @Value("${node.url}")
    String URL;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Search.ResponseDTO getStockNameFromTicker(String ticker) {
        Search.ResponseDTO result = new Search.ResponseDTO();

        try {
            result = restTemplate.getForObject(URL + "/stocks/search/" + ticker, Search.ResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
