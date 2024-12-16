package com.aparaskov.currency_rate_downloader.repository;

import com.aparaskov.currency_rate_downloader.model.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static com.aparaskov.currency_rate_downloader.util.TestUtil.buildValidCurrency;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CurrencyRepositoryTest {
    @Autowired
    private CurrencyRepository currencyRepository;

    @BeforeEach
    public void setUp(){
        currencyRepository.save(buildValidCurrency());
    }

    @Test
    public void saveCurrencyTest() {
        Currency currency = buildValidCurrency();

        currencyRepository.save(currency);

        assertEquals(2, currencyRepository.findAll().size());
    }

    @Test
    public void findByNameTest() {
        Optional<Currency> actualOptionalCurrency = currencyRepository.findByName("name");

        assertTrue(actualOptionalCurrency.isPresent());
    }
}
