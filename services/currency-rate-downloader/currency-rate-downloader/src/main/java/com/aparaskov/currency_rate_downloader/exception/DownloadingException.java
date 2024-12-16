package com.aparaskov.currency_rate_downloader.exception;

public class DownloadingException extends RuntimeException{
    public DownloadingException(String message) {
        super(message);
    }
}
