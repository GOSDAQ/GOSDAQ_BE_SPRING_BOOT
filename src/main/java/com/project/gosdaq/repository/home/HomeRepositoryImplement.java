package com.project.gosdaq.repository.home;

import com.project.gosdaq.dto.home.Have;
import com.project.gosdaq.dto.home.Interest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class HomeRepositoryImplement implements HomeRepository {

    @Value("${node.url}")
    String URL;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Interest.NodeResponseDTO getInterestData(String ticker, int period) {

        Interest.NodeResponseDTO result = new Interest.NodeResponseDTO();

        try {
            result = restTemplate.getForObject(URL + "/stocks/interest?ticker=" + ticker + "&period=" + period, Interest.NodeResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Have.NodeResponseDTO getHaveData(String ticker) {
        Have.NodeResponseDTO result = new Have.NodeResponseDTO();

        try {
            result = restTemplate.getForObject(URL + "/stocks/have/" + ticker, Have.NodeResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}