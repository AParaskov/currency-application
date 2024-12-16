package com.aparaskov.currency_rate_downloader.repository;

import com.aparaskov.currency_rate_downloader.model.Rate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.aparaskov.currency_rate_downloader.util.TestUtil.buildValidRate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RateRepositoryTest {
    @Autowired
    private RateRepository rateRepository;

    @BeforeEach
    public void setUp() {
        rateRepository.save(buildValidRate());
    }

    @Test
    public void saveRateTest() {
        Rate rate = buildValidRate();

        rateRepository.save(rate);

        assertEquals(2, rateRepository.findAll().size());
    }

    @Test
    public void findFirstByOrderByCurrentRateDateDescTest() {
        Optional<Rate> actualOptionalRate = rateRepository.findFirstByOrderByCurrentRateDateDesc();

        assertTrue(actualOptionalRate.isPresent());
    }
}
