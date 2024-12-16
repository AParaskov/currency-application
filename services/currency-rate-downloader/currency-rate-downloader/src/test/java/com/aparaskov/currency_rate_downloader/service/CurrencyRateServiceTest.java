package com.aparaskov.currency_rate_downloader.service;

import com.aparaskov.currency_rate_downloader.exception.NoNewCurrencyRateException;
import com.aparaskov.currency_rate_downloader.mapper.CurrencyRateMapper;
import com.aparaskov.currency_rate_downloader.model.Currency;
import com.aparaskov.currency_rate_downloader.model.Rate;
import com.aparaskov.currency_rate_downloader.model.dto.response.LatestCurrencyRateResponse;
import com.aparaskov.currency_rate_downloader.repository.CurrencyRepository;
import com.aparaskov.currency_rate_downloader.repository.RateRepository;
import com.aparaskov.currency_rate_downloader.service.impl.CurrencyRateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static com.aparaskov.currency_rate_downloader.util.TestUtil.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CurrencyRateServiceTest {
    private CurrencyRateService currencyRateService;
    private CurrencyRateMapper mapper;
    private CurrencyRepository currencyRepository;
    private RateRepository rateRepository;
    private NotificationService notificationService;
    private DateTimeFormatter dateTimeFormatter;

    @BeforeEach
    public void setUp() {
        currencyRepository = Mockito.mock(CurrencyRepository.class);
        rateRepository = Mockito.mock(RateRepository.class);
        mapper = Mockito.mock(CurrencyRateMapper.class);
        notificationService = Mockito.mock(NotificationService.class);
        dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        currencyRateService = new CurrencyRateServiceImpl(currencyRepository, rateRepository, mapper, notificationService, dateTimeFormatter);

        when(mapper.rateDtoToLatestCurrencyRateDto(any())).thenReturn(buildValidLatestCurrencyRateDto());
        when(mapper.rateDtoToRate(any())).thenReturn(new Rate());
        when(mapper.rateDtoToCurrency(any())).thenReturn(new Currency());
        ReflectionTestUtils.setField(currencyRateService, "BNB_URL_BG", "file:./src/test/resources/static/currency_rate.xml");
        ReflectionTestUtils.setField(currencyRateService, "BNB_URL_EN", "file:./src/test/resources/static/currency_rate.xml");
    }

    @Test
    public void testGetLatestCurrencyRates() {
        LatestCurrencyRateResponse expectedResponse = buildValidLatestCurrencyRateResponse();
        when(currencyRepository.findByName(any())).thenReturn(Optional.empty());
        when(rateRepository.findFirstByOrderByCurrentRateDateDesc()).thenReturn(Optional.empty());

        LatestCurrencyRateResponse actualResponse = currencyRateService.getLatestCurrencyRates();

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testGetLatestCurrencyRatesThrowsNoNewCurrencyRateException() {
        when(rateRepository.findFirstByOrderByCurrentRateDateDesc()).thenReturn(Optional.of(buildValidRate()));

        assertThrows(NoNewCurrencyRateException.class, () -> currencyRateService.getLatestCurrencyRates());
    }
}
