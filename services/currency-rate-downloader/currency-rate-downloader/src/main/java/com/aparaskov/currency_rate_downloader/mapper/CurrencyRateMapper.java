package com.aparaskov.currency_rate_downloader.mapper;

import com.aparaskov.currency_rate_downloader.model.Currency;
import com.aparaskov.currency_rate_downloader.model.Rate;
import com.aparaskov.currency_rate_downloader.model.dto.response.LatestCurrencyRateDto;
import com.aparaskov.currency_rate_downloader.model.dto.xml.RateDto;
import com.aparaskov.currency_rate_downloader.model.enumeration.CurrencyCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CurrencyRateMapper {
    private final DateTimeFormatter formatter;

    public Currency rateDtoToCurrency(RateDto rateDto) {
        return Currency.builder()
                .name(rateDto.getName() != null ? rateDto.getName() : null)
                .build();
    }

    public Rate rateDtoToRate(RateDto rateDto) {
        return Rate.builder()
                .rate(rateDto.getRate() != null ? Double.parseDouble(rateDto.getRate()) : null)
                .reverseRate(rateDto.getReverseRate() != null ? Double.parseDouble(rateDto.getReverseRate()) : null)
                .currentRateDate(rateDto.getCurrentDate() != null ? LocalDate.parse(rateDto.getCurrentDate(), formatter) : null)
                .currencyCode(rateDto.getCode() != null ? CurrencyCode.valueOf(rateDto.getCode()) : null)
                .gold(rateDto.getGold() != null ? Integer.parseInt(rateDto.getGold()) : null)
                .fStar(rateDto.getFStar() != null ? Integer.parseInt(rateDto.getFStar()) : null)
                .ratio(rateDto.getRatio() != null ? Integer.parseInt(rateDto.getRatio()) : null)
                .build();
    }

    public LatestCurrencyRateDto rateDtoToLatestCurrencyRateDto(RateDto rateDto) {
        return LatestCurrencyRateDto.builder()
                .currentDate(rateDto.getCurrentDate())
                .code(rateDto.getCode())
                .ratio(rateDto.getRatio())
                .reverseRate(rateDto.getReverseRate())
                .rate(rateDto.getRate())
                .name(rateDto.getName())
                .fStar(rateDto.getFStar())
                .gold(rateDto.getGold())
                .build();
    }
}
