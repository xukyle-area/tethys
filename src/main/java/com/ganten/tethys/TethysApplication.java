package com.ganten.tethys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TethysApplication {

    public static void main(String[] args) {
        SpringApplication.run(TethysApplication.class, args);
    }
}
