package com.aparaskov.currency_rate_consumer.ws;

import com.aparaskov.currency_rate_consumer.service.LatestCurrencyRateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Component
@RequiredArgsConstructor
@Slf4j
public class WSConfig {
    private final LatestCurrencyRateService latestCurrencyRateService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("----------Initialize WebSocket----------");
        WebSocketClient webSocketClient = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        stompClient.setInboundMessageSizeLimit(128 * 1024);
        stompClient.setOutboundMessageSizeLimit(128 * 1024);

        String url = "ws://localhost:8090/notification-server";
        StompSessionHandler sessionHandler = new WSSessionHandler(latestCurrencyRateService);
        stompClient.connectAsync(url, sessionHandler);
    }
}
