package com.aparaskov.currency_rate_consumer.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PayloadDto {
    private String gold;
    private String name;
    private String code;
    private String ratio;
    private String reverseRate;
    private String rate;
    private String currentDate;
    private String fStar;
}
