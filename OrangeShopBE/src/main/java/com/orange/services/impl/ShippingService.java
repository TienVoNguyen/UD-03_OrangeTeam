package com.orange.services.impl;

import com.orange.domain.model.GHN.GHNService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ShippingService {
    private final String GHNToken = "845cb13c-b64c-11ed-9a6e-422f22df4aa9";

    private final RestTemplate restTemplate;

    public GHNService getService(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", "845cb13c-b64c-11ed-9a6e-422f22df4aa9");
        return null;
    }



    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
