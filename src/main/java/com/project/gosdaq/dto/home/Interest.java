package com.project.gosdaq.dto.home;

import lombok.Data;

import java.util.List;

public class Interest {

    @Data
    public static class RequestDTO{
        private List<String> tickers;
    }

    @Data
    public static class HistoryDTO{
        private String date;
        private float close;
    }

    @Data
    public static class ResponseDataDTO{
        private String ticker;
        private String name;
        private float price;
        private float rate;
        private List<HistoryDTO> history;
        private int cnt;
    }

    @Data
    public static class NodeResponseDTO{
        private int code;
        private String msg;
        private ResponseDataDTO data;
    }

    @Data
    public static class ResponseDTO{
        private int code;
        private String msg;
        private List<ResponseDataDTO> data;
    }
}
