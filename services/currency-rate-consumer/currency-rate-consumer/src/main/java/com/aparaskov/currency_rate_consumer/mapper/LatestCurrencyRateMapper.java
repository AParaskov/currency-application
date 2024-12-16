package com.aparaskov.currency_rate_consumer.mapper;

import com.aparaskov.currency_rate_consumer.entity.LatestCurrencyRate;
import com.aparaskov.currency_rate_consumer.entity.dto.PayloadDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class LatestCurrencyRateMapper {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public LatestCurrencyRate fromDtoToLatestCurrencyRate(PayloadDto dto) {
        return LatestCurrencyRate.builder()
                .name(dto.getName() != null ? dto.getName() : null)
                .rate(dto.getRate() != null ? Double.parseDouble(dto.getRate()) : null)
                .reverseRate(dto.getReverseRate() != null ? Double.parseDouble(dto.getReverseRate()) : null)
                .currentRateDate(dto.getCurrentDate() != null ? LocalDate.parse(dto.getCurrentDate(), formatter) : null)
                .currencyCode(dto.getCode() != null ? dto.getCode() : null)
                .gold(dto.getGold() != null ? Integer.parseInt(dto.getGold()) : null)
                .fStar(dto.getFStar() != null ? Integer.parseInt(dto.getFStar()) : null)
                .ratio(dto.getRatio() != null ? Integer.parseInt(dto.getRatio()) : null)
                .build();
    }
}
