package com.aparaskov.currency_rate_downloader.service;

import com.aparaskov.currency_rate_downloader.model.dto.notification.NotificationDto;

public interface NotificationService {
    void sendNotification(String topic, NotificationDto payload);
}
