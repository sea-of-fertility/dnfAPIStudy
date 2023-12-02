package com.example.dnfapistudy.controller;

import com.example.dnfapistudy.api.ServerInform;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class ServerController {

    private final RestTemplate restTemplate;

    @Value("${api-key}")
    private String apiKey;
    private String url = "https://api.neople.co.kr/df/servers?apikey=%s";

    @GetMapping("/")
    public ServerInform serverInform() {
        ServerInform forObject = restTemplate.getForObject(String.format(url, apiKey), ServerInform.class);
        return  forObject;
    }
}
