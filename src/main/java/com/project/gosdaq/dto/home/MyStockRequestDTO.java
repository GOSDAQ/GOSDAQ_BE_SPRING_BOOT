package com.project.gosdaq.dto.home;

import lombok.Data;

import java.util.List;

@Data
public class MyStockRequestDTO {
    private List<StockInfo> data;

    public static class StockInfo {
        public String ticker;
        public double avg;
        public int amt;
    }
}
