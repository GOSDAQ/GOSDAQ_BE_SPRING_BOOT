package com.project.gosdaq.common;

import com.project.gosdaq.dto.home.HomeResponseDTO;
import org.springframework.web.client.RestTemplate;

public class NodeApi {
    String URL = "http://localhost:4000/";

    public HomeResponseDTO requestToNode(String path, String ticker) throws Exception{
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(URL + path + ticker, HomeResponseDTO.class);
    }
}
