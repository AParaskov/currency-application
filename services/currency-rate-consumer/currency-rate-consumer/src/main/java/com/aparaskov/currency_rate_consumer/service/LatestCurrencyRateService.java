package com.aparaskov.currency_rate_consumer.service;

import com.aparaskov.currency_rate_consumer.entity.dto.NotificationDto;

public interface LatestCurrencyRateService {
    void save(NotificationDto payload);
}
