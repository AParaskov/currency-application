package com.aparaskov.currency_rate_consumer.ws;

import com.aparaskov.currency_rate_consumer.entity.dto.NotificationDto;
import com.aparaskov.currency_rate_consumer.service.LatestCurrencyRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

@Component
@RequiredArgsConstructor
@Slf4j
public class WSSessionHandler extends StompSessionHandlerAdapter {
    private final LatestCurrencyRateService latestCurrencyRateService;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("New session started : " + session.getSessionId());
        session.subscribe("/topic/notification", this);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return NotificationDto.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        NotificationDto message = (NotificationDto) payload ;
        log.info("Received payload: {}", message);
        latestCurrencyRateService.save(message);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        throw new RuntimeException("Failure in WebSocket handling", exception);
    }
}
