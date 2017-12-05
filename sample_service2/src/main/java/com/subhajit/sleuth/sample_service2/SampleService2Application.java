package com.subhajit.sleuth.sample_service2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class SampleService2Application {

    public static void main(String[] args) {
        SpringApplication.run(SampleService2Application.class, args);
    }
    
    // For make sleuth work we need to create a bean and also it needs to autowired while using
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
