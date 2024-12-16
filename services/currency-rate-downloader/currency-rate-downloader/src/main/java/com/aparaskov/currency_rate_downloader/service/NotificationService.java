package com.aparaskov.currency_rate_downloader.service;

import com.aparaskov.currency_rate_downloader.model.dto.notification.NotificationDto;
import com.aparaskov.currency_rate_downloader.model.dto.response.LatestCurrencyRateResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface NotificationService {
    void sendNotification(String topic, NotificationDto payload);
}
