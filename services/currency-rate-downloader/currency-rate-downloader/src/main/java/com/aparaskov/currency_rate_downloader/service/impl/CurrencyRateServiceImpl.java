package com.aparaskov.currency_rate_downloader.service.impl;

import com.aparaskov.currency_rate_downloader.exception.NoNewCurrencyRateException;
import com.aparaskov.currency_rate_downloader.mapper.CurrencyRateMapper;
import com.aparaskov.currency_rate_downloader.model.Currency;
import com.aparaskov.currency_rate_downloader.model.Rate;
import com.aparaskov.currency_rate_downloader.model.dto.notification.NotificationDto;
import com.aparaskov.currency_rate_downloader.model.dto.response.LatestCurrencyRateDto;
import com.aparaskov.currency_rate_downloader.model.dto.response.LatestCurrencyRateResponse;
import com.aparaskov.currency_rate_downloader.model.dto.xml.CurrencyRateDto;
import com.aparaskov.currency_rate_downloader.repository.CurrencyRepository;
import com.aparaskov.currency_rate_downloader.repository.RateRepository;
import com.aparaskov.currency_rate_downloader.service.CurrencyRateService;
import com.aparaskov.currency_rate_downloader.service.NotificationService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.aparaskov.currency_rate_downloader.exception.ErrorCodesAndMessages.NO_NEW_CURRENCY_RATE_DESCRIPTION;

@Service
@RequiredArgsConstructor
public class CurrencyRateServiceImpl implements CurrencyRateService {
    @Value("${bnb.url.bg}")
    private String BNB_URL_BG;
    @Value("${bnb.url.en}")
    private String BNB_URL_EN;
    @Value("${ws.topic.name}")
    private String TOPIC_NAME;
    private final CurrencyRepository currencyRepository;
    private final RateRepository rateRepository;
    private final CurrencyRateMapper mapper;
    private final NotificationService notificationService;
    private final DateTimeFormatter formatter;


    @Override
    public LatestCurrencyRateResponse getLatestCurrencyRates() {
        LocalDate now = LocalDate.now();
        Optional<Rate> lastDbRecordDate = rateRepository.findFirstByOrderByCurrentRateDateDesc();

        if (lastDbRecordDate.isEmpty()) {
            return buildResponse();
        } else if (now.isAfter(lastDbRecordDate.get().getCurrentRateDate())) {
            return buildResponse();
        }

        notificationService.sendNotification(TOPIC_NAME, NotificationDto.builder()
                .payload(List.of(LatestCurrencyRateDto.builder()
                        .ratio("ratio")
                        .reverseRate("reverseRate")
                        .name("name")
                        .gold("gold")
                        .fStar("fStar")
                        .code("code")
                        .currentDate("currentDate")
                        .rate("rate")
                        .build()))
                .build());
        throw new NoNewCurrencyRateException(NO_NEW_CURRENCY_RATE_DESCRIPTION);
    }

    private LatestCurrencyRateResponse buildResponse() {
        List<LatestCurrencyRateDto> currenciesEnAndBg = new ArrayList<>();

        currenciesEnAndBg.addAll(downloadCurrenciesAndSendNotification(BNB_URL_EN));
        currenciesEnAndBg.addAll(downloadCurrenciesAndSendNotification(BNB_URL_BG));

        if (currenciesEnAndBg.isEmpty()) {
            throw new NoNewCurrencyRateException(NO_NEW_CURRENCY_RATE_DESCRIPTION);
        }

        return LatestCurrencyRateResponse.builder()
                .rate(currenciesEnAndBg)
                .build();
    }

    private List<LatestCurrencyRateDto> saveCurrencyRates(CurrencyRateDto currencyRateDto) {
        List<LatestCurrencyRateDto> currencies = new ArrayList<>();

        currencyRateDto.getRates().stream().skip(1).forEach(rateDto -> {
            Optional<Currency> optCurrency = currencyRepository.findByName(rateDto.getName());

            if (optCurrency.isPresent()) {
                currencies.add(mapper.rateDtoToLatestCurrencyRateDto(rateDto));

                Rate rate = mapper.rateDtoToRate(rateDto);
                rate.setCurrency(optCurrency.get());

                rateRepository.save(rate);
            } else {
                currencies.add(mapper.rateDtoToLatestCurrencyRateDto(rateDto));

                Currency currency = mapper.rateDtoToCurrency(rateDto);

                Rate rate = mapper.rateDtoToRate(rateDto);
                rate.setCurrency(currency);

                currencyRepository.save(currency);
                rateRepository.save(rate);
            }
        });

        return currencies;
    }

    private List<LatestCurrencyRateDto> downloadCurrenciesAndSendNotification(String url) {
        try {
            URL bnbUrl = new URL(url);
            try (InputStream in = bnbUrl.openStream()) {
                Optional<Rate> lastDbRecord = rateRepository.findFirstByOrderByCurrentRateDateDesc();
                CurrencyRateDto unmarshalledCurrencies = unmarshallFile(in);
                LocalDate currentDateFromBnb = LocalDate.parse(unmarshalledCurrencies.getRates().get(1).getCurrentDate(), formatter);
                Optional<Currency> currencyExistsInDb = currencyRepository.findByName(unmarshalledCurrencies.getRates().get(1).getName());

                if (lastDbRecord.isEmpty() || currencyExistsInDb.isEmpty()) {
                    List<LatestCurrencyRateDto> savedCurrencies = saveCurrencyRates(unmarshalledCurrencies);

                    notificationService.sendNotification(TOPIC_NAME, NotificationDto.builder()
                            .payload(savedCurrencies)
                            .build());

                    return savedCurrencies;
                }

                if (currentDateFromBnb.isAfter(lastDbRecord.get().getCurrentRateDate())) {
                    List<LatestCurrencyRateDto> savedCurrencies = saveCurrencyRates(unmarshalledCurrencies);

                    notificationService.sendNotification(TOPIC_NAME, NotificationDto.builder()
                            .payload(savedCurrencies)
                            .build());

                    return saveCurrencyRates(unmarshalledCurrencies);
                }

                return Collections.emptyList();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private CurrencyRateDto unmarshallFile(InputStream inputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(CurrencyRateDto.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (CurrencyRateDto) jaxbUnmarshaller.unmarshal(inputStream);
    }
}
