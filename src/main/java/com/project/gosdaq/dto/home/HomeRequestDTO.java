package com.project.gosdaq.dto.home;

import lombok.Data;

import java.util.ArrayList;

@Data
public class HomeRequestDTO {
    private ArrayList<String> tickers;
}
