package com.aparaskov.currency_rate_downloader.controller;

import com.aparaskov.currency_rate_downloader.service.CurrencyRateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.aparaskov.currency_rate_downloader.util.TestUtil.DOWNLOAD_CURRENCIES_URL;
import static com.aparaskov.currency_rate_downloader.util.TestUtil.INCORRECT_URL;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CurrencyRateDownloaderController.class)
@ActiveProfiles("test")
public class CurrencyRateDownloaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CurrencyRateService currencyRateService;

    @Test
    public void getCurrencyRateShouldReturn200Ok() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(DOWNLOAD_CURRENCIES_URL))
                .andExpect(status().isOk());
    }

    @Test
    public void getCurrencyRateShouldReturn404NotFoundWhenUrlIsIncorrect() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(INCORRECT_URL))
                .andExpect(status().isNotFound());
    }
}
