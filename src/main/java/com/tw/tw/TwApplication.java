package com.tw.tw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class TwApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwApplication.class, args);
    }

}
