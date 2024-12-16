package com.aparaskov.currency_rate_downloader.model;

import com.aparaskov.currency_rate_downloader.model.enumeration.CurrencyCode;
import jakarta.persistence.*;
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
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer ratio;

    private Double rate;

    @Column(name = "reverse_rate")
    private Double reverseRate;

    @Enumerated(EnumType.STRING)
    private CurrencyCode currencyCode;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @Column(name = "current_rate_date")
    private LocalDate currentRateDate;

    private Integer gold;

    @Column(name = "f_star")
    private Integer fStar;
}
