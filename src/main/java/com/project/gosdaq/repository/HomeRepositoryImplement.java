package com.project.gosdaq.repository;

import com.project.gosdaq.common.NodeApi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HomeRepositoryImplement implements HomeRepository {
    @Override
    public String getStockData(List<String> tickers) {
        int cnt = 0;

        for(String ticker : tickers){
            NodeApi nodeApi = new NodeApi();

            try {
                String a = nodeApi.requstToNode(ticker);
                System.out.println("AAAAAAAAAAAAAAAAA");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cnt++;
            }
        }

        return ""+cnt;
    }
}
