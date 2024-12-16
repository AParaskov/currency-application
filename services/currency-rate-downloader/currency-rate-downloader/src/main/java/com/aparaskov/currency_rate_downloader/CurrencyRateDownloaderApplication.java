package com.aparaskov.currency_rate_downloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@SpringBootApplication
public class CurrencyRateDownloaderApplication {
	public static void main(String[] args) {
		SpringApplication.run(CurrencyRateDownloaderApplication.class, args);
	}

}
