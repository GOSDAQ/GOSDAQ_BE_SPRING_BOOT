package com.project.gosdaq.dto.home;

import lombok.Data;

import java.util.List;

@Data
public class InterestRequestDTO {
    private List<String> tickers;
}
