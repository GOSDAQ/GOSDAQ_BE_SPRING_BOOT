package com.project.gosdaq.service.home;

import java.util.HashMap;
import java.util.List;

public interface HomeService {
    HashMap<String, Object> getInterest(List<String> tickers);
}
