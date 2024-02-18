package com.hack.cvcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CvCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvCenterApplication.class, args);
    }

}
