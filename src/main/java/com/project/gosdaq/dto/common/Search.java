package com.project.gosdaq.dto.common;

import lombok.Data;

@Data
public class Search {

    @Data
    public static class ResponseDataDTO{
        private String name;
        private String ticker;
    }

    @Data
    public static class ResponseDTO{
        private int code;
        private String msg;
        private ResponseDataDTO data;
    }
}
