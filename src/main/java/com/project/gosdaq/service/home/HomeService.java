package com.project.gosdaq.service.home;

import com.project.gosdaq.dto.home.Have;
import com.project.gosdaq.dto.home.Interest;

import java.util.List;

public interface HomeService {
    Interest.ResponseDTO getInterest(List<String> tickers);
    Have.ResponseDTO getHave(Have.RequestDTO dto);
}

