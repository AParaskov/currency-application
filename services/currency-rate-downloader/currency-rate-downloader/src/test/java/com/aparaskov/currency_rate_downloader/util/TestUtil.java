package com.aparaskov.currency_rate_downloader.util;

import com.aparaskov.currency_rate_downloader.model.Currency;
import com.aparaskov.currency_rate_downloader.model.Rate;
import com.aparaskov.currency_rate_downloader.model.dto.response.LatestCurrencyRateDto;
import com.aparaskov.currency_rate_downloader.model.dto.response.LatestCurrencyRateResponse;
import com.aparaskov.currency_rate_downloader.model.enumeration.CurrencyCode;

import java.time.LocalDate;
import java.util.List;

public class TestUtil {
    public static final String DOWNLOAD_CURRENCIES_URL = "/download-currencies";
    public static final String INCORRECT_URL = "/incorrect-url";

    public static LatestCurrencyRateDto buildValidLatestCurrencyRateDto() {
        return LatestCurrencyRateDto.builder()
                .rate("rate")
                .currentDate("12.12.2024")
                .code("code")
                .fStar("fStar")
                .gold("gold")
                .name("name")
                .reverseRate("reverseRate")
                .ratio("ratio")
                .build();
    }
    public static LatestCurrencyRateResponse buildValidLatestCurrencyRateResponse() {
        return LatestCurrencyRateResponse.builder()
                .rate(List.of(buildValidLatestCurrencyRateDto(), buildValidLatestCurrencyRateDto()))
                .build();
    }

    public static Rate buildValidRate() {
        return Rate.builder()
                .currencyCode(CurrencyCode.AUD)
                .rate(0.10)
                .id(1L)
                .ratio(1)
                .reverseRate(0.10)
                .fStar(1)
                .gold(1)
                .currentRateDate(LocalDate.now())
                .currency(new Currency())
                .build();
    }

    public static Currency buildValidCurrency() {
        return Currency.builder()
                .id(1L)
                .name("name")
                .build();
    }
}
