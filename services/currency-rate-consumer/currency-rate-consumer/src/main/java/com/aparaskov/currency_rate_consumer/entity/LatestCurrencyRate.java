package com.aparaskov.currency_rate_consumer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
