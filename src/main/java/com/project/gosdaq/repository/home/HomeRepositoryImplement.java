package com.project.gosdaq.repository.home;

import com.project.gosdaq.dto.home.Have;
import com.project.gosdaq.dto.home.Interest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class HomeRepositoryImplement implements HomeRepository {

    @Autowired
    RestTemplate restTemplate;

//    String URL = "https://gosdaq-node.herokuapp.com/stocks/";
    String URL = "http://localhost:4000/stocks/";

    @Override
    public Interest.NodeResponseDTO getInterestData(String ticker, int period) {
        Interest.NodeResponseDTO result = new Interest.NodeResponseDTO();

        try {
            result = restTemplate.getForObject(URL + "interest?ticker=" + ticker + "&period=" + period, Interest.NodeResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Have.NodeResponseDTO getHaveData(String ticker) {
        Have.NodeResponseDTO result = new Have.NodeResponseDTO();

        try {
            result = restTemplate.getForObject(URL + "have/" + ticker, Have.NodeResponseDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}