package com.aparaskov.currency_rate_downloader.controller;

import com.aparaskov.currency_rate_downloader.model.dto.response.LatestCurrencyRateResponse;
import com.aparaskov.currency_rate_downloader.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyRateDownloaderController {
    private final CurrencyRateService currencyRateService;

    @GetMapping(value = "/download-currencies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LatestCurrencyRateResponse> getCurrencyRates() {
        return ResponseEntity.ok(currencyRateService.getLatestCurrencyRates());
    }

}
