package com.aparaskov.currency_rate_consumer.service.impl;

import com.aparaskov.currency_rate_consumer.entity.dto.NotificationDto;
import com.aparaskov.currency_rate_consumer.mapper.LatestCurrencyRateMapper;
import com.aparaskov.currency_rate_consumer.repository.LatestCurrencyRateRepository;
import com.aparaskov.currency_rate_consumer.service.LatestCurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LatestCurrencyRateServiceImpl implements LatestCurrencyRateService {
    private final LatestCurrencyRateRepository repository;
    private final LatestCurrencyRateMapper mapper;

    @Override
    public void save(NotificationDto payload) {
        payload.getPayload().forEach(rate -> {
            repository.save(mapper.fromDtoToLatestCurrencyRate(rate));
        });
    }
}
