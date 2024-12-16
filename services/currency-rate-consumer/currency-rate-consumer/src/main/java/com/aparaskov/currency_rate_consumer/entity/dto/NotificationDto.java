package com.aparaskov.currency_rate_consumer.entity.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NotificationDto {
    List<PayloadDto> payload;
}
