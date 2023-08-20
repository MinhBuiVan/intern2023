package com.example.leadsgen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LeadsgenApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeadsgenApplication.class, args);
    }

}
