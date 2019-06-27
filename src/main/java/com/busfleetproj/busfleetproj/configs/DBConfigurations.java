package com.busfleetproj.busfleetproj.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")

public class DBConfigurations {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    @Profile("dev")
    @Bean
    public String devDatabaseConnection(){
        System.out.println("DB connection for DEV - H2");
        return "DB connection for DEV - H2";
    }

    @Profile("test")
    @Bean
    public String testDatabaseConnection(){
        System.out.println("DB connection for TEST - MYSQL");
        return "DB connection for TEST - MYSQL";
    }

    @Profile("demo")
    @Bean
    public String demoDatabaseConnection(){
        System.out.println("DB connection for DEMO - MYSQL");
        return "DB connection for DEMO - MYSQL";
    }
}
