package com.orange.controller;

import com.google.gson.JsonObject;
import com.orange.common.payload.Result;
import com.orange.domain.model.GHN.GHNProvince;
import com.orange.payload.response.GHNProvinceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/shipping/test")
@RequiredArgsConstructor
public class GHNShippingController {

    @GetMapping
    public ResponseEntity<?> getProvince(){

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", "845cb13c-b64c-11ed-9a6e-422f22df4aa9");

        HttpEntity requestEntity = new HttpEntity( headers);

        return restTemplate.exchange("https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province", HttpMethod.GET, requestEntity, GHNProvinceResponse.class);

    }
}
