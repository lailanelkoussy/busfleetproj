package com.busfleetproj.busfleetproj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Slf4j
@EnableSwagger2
public class BusfleetprojApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusfleetprojApplication.class, args);
    }

}
