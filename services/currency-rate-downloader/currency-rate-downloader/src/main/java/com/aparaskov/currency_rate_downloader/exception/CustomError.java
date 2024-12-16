package com.aparaskov.currency_rate_downloader.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomError {
    private String timestamp;
    private String errorCode;
    private String errorMessage;
    private String description;
}
