package com.project.gosdaq.repository.common;

import java.util.HashMap;

public interface CommonRepository {
    HashMap<String, Object> getStockNameFromTicker(String ticker);
}
