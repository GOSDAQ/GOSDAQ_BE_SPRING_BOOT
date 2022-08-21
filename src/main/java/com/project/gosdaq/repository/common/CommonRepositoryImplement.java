package com.project.gosdaq.repository.common;

import com.project.gosdaq.common.NodeApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class CommonRepositoryImplement implements CommonRepository{
    private final NodeApi nodeApi;

    @Autowired
    public CommonRepositoryImplement(NodeApi nodeApi) {
        this.nodeApi = nodeApi;
    }

    @Override
    public HashMap<String, Object> getStockNameFromTicker(String ticker) {
        return null;
    }
}
