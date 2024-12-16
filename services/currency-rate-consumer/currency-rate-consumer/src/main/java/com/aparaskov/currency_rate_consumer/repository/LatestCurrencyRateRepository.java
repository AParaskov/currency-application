package com.aparaskov.currency_rate_consumer.repository;

import com.aparaskov.currency_rate_consumer.entity.LatestCurrencyRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LatestCurrencyRateRepository extends JpaRepository<LatestCurrencyRate, Long> {
}
