package com.aparaskov.currency_rate_downloader.service;

import com.aparaskov.currency_rate_downloader.model.dto.response.LatestCurrencyRateResponse;

public interface CurrencyRateService {
    LatestCurrencyRateResponse getLatestCurrencyRates();
}
