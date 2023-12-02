package com.example.dnfapistudy;

import com.example.dnfapistudy.api.ServerInform;
import com.example.dnfapistudy.properties.StorageProperties;
import com.example.dnfapistudy.service.StorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class DnfApiStudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(DnfApiStudyApplication.class, args);
    }


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return  builder.build();
    }


    @Bean
    ApplicationRunner applicationRunner(StorageService storageService) {
        return args -> {
            storageService.init();
        };
    }

}
