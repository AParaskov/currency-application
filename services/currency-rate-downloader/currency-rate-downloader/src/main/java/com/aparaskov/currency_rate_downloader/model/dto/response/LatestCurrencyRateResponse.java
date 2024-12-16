package com.aparaskov.currency_rate_downloader.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LatestCurrencyRateResponse {
    List<LatestCurrencyRateDto> rate;
}