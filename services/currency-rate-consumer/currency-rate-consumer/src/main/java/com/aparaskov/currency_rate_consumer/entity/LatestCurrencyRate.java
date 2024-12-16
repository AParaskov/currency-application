package com.aparaskov.currency_rate_consumer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "latest_currency_rate")
public class LatestCurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer ratio;

    private Double rate;

    @Column(name = "reverse_rate")
    private Double reverseRate;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "current_rate_date")
    private LocalDate currentRateDate;

    private Integer gold;

    @Column(name = "f_star")
    private Integer fStar;
}
