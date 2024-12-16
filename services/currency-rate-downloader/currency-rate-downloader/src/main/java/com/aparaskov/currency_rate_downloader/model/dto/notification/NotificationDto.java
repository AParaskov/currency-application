package com.aparaskov.currency_rate_downloader.model.dto.notification;

import com.aparaskov.currency_rate_downloader.model.dto.response.LatestCurrencyRateDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationDto {
    List<LatestCurrencyRateDto> payload;
}
