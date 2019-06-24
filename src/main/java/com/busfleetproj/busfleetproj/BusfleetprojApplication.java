package com.busfleetproj.busfleetproj;

import com.busfleetproj.busfleetproj.repos.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class BusfleetprojApplication {

    public static void main(String[] args) {
        SpringApplication.run(BusfleetprojApplication.class, args);
    }

}
