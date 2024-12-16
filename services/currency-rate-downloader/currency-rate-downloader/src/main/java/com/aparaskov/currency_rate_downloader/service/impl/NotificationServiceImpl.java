package com.aparaskov.currency_rate_downloader.service.impl;

import com.aparaskov.currency_rate_downloader.model.dto.notification.NotificationDto;
import com.aparaskov.currency_rate_downloader.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService {
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendNotification(String topic, NotificationDto payload) {
        messagingTemplate.convertAndSend(topic, payload);
        log.info("Notification sent with payload: {}", payload);
    }
}
