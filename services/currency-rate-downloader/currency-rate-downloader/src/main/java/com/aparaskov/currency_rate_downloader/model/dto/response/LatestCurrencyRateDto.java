package com.aparaskov.currency_rate_downloader.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class LatestCurrencyRateDto {
    private String gold;
    private String name;
    private String code;
    private String ratio;
    private String reverseRate;
    private String rate;
    private String currentDate;
    private String fStar;
}
