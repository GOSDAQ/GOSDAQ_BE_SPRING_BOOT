package com.project.gosdaq.dto.home;

import lombok.Data;

import java.util.List;

public class Have {

    @Data
    public static class RequestDataDTO{
        private String ticker;
        private float avg;
        private int amt;
    }

    @Data
    public static class RequestDTO{
        private List<RequestDataDTO> data;
    }

    @Data
    public static class ResponseStockListDataDTO{
        private String ticker;
        private String revenue;
    }

    @Data
    public static class ResponseDataDTO{
        private List<ResponseStockListDataDTO> list;
        private double exchange;
    }

    @Data
    public static class NodeResponseListDataDTO{
        private float price;
        private float rate;
    }

    @Data
    public static class NodeResponseDTO{
        private int code;
        private String msg;
        private NodeResponseListDataDTO data;
    }

    @Data
    public static class ResponseDTO{
        private int code;
        private String msg;
        private ResponseDataDTO data;
    }
}
