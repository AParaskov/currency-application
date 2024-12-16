package com.aparaskov.currency_rate_downloader.repository;

import com.aparaskov.currency_rate_downloader.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Optional<Rate> findFirstByOrderByCurrentRateDateDesc();
}
