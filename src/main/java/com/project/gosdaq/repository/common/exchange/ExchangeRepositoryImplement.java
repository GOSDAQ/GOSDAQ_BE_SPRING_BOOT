package com.project.gosdaq.repository.common.exchange;

import com.project.gosdaq.dto.common.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class ExchangeRepositoryImplement implements ExchangeRepository{

    @Value("${node.url}")
    String URL;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Exchange.ResponseDTO getExchangeRate() {
        Exchange.ResponseDTO result = new Exchange.ResponseDTO();

        try {
            result = restTemplate.getForObject(URL + "/common/exchange", Exchange.ResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
