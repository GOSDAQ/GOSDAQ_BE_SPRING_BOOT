package com.project.gosdaq.repository.home;

import com.project.gosdaq.dto.home.Have;
import com.project.gosdaq.dto.home.Interest;

public interface HomeRepository {
    Interest.NodeResponseDTO getInterestData(String tickers, int period);
    Have.NodeResponseDTO getHaveData(String ticker);
}


