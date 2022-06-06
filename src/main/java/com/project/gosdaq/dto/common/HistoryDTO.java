package com.project.gosdaq.dto.common;

import lombok.Data;

import java.util.List;

@Data
public class HistoryDTO {
    private List<Object> data;
    private int cnt;
}

