package com.aparaskov.currency_rate_downloader.exception;

public class NoNewCurrencyRateException extends RuntimeException{
    public NoNewCurrencyRateException(String message) {
        super(message);
    }
}
