package com.project.gosdaq.repository.common.exchange;

import com.project.gosdaq.dto.common.Exchange;

public interface ExchangeRepository {
    Exchange.ResponseDTO getExchangeRate();
}
