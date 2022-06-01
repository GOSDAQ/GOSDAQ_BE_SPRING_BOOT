package com.project.gosdaq.repository;

import com.project.gosdaq.common.NodeApi;
import com.project.gosdaq.dto.home.HomeResponseDTO;
import org.springframework.stereotype.Repository;

@Repository
public class HomeRepositoryImplement implements HomeRepository {
    @Override
    public HomeResponseDTO getStockData(String ticker) {

        NodeApi nodeApi = new NodeApi();
        HomeResponseDTO result = new HomeResponseDTO();

        try {
            result =  nodeApi.requestToNode("stocks/interest/", ticker);
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }

        return result;
    }
}