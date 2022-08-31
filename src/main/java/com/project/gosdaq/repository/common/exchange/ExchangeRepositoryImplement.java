package com.project.gosdaq.repository.common.exchange;

import com.project.gosdaq.dto.common.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ExchangeRepositoryImplement implements ExchangeRepository{

    @Autowired
    RestTemplate restTemplate;

//    String URL = "https://gosdaq-node.herokuapp.com/common/";
        String URL = "http://localhost:4000/common/";

    @Override
    public Exchange.ResponseDTO getExchangeRate() {
        Exchange.ResponseDTO result = new Exchange.ResponseDTO();

        try {
            result = restTemplate.getForObject(URL + "exchange", Exchange.ResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
