package com.aparaskov.currency_rate_downloader.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.aparaskov.currency_rate_downloader.exception.ErrorCodesAndMessages.BAD_REQUEST_ERROR;
import static com.aparaskov.currency_rate_downloader.exception.ErrorCodesAndMessages.NO_NEW_CURRENCY_RATE_MESSAGE;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(NoNewCurrencyRateException.class)
    public ResponseEntity<Object> handleNoNewCurrencyRateException(NoNewCurrencyRateException ex) {
        String errorMessageDescription = checkIfErrorMessageDescriptionIsValid(ex.getMessage());

        CustomError errorMessage = CustomError.builder()
                .timestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .errorCode(BAD_REQUEST_ERROR)
                .errorMessage(NO_NEW_CURRENCY_RATE_MESSAGE)
                .description(errorMessageDescription)
                .build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private String checkIfErrorMessageDescriptionIsValid(String errorMessageDescription) {
        return errorMessageDescription == null ? "" : errorMessageDescription;
    }
}
