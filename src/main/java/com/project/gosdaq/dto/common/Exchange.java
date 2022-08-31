package com.project.gosdaq.dto.common;

import lombok.Data;

public class Exchange {

    @Data
    public static class ExchangeRateDTO{
        private double exchange;
    }

    @Data
    public static class ResponseDTO{
        private int code;
        private String msg;
        private ExchangeRateDTO data;
    }
}
