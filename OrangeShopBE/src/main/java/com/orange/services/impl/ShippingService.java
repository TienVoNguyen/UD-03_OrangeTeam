package com.orange.services.impl;

import com.orange.domain.model.GHN.GHNProvince;
import com.orange.domain.model.GHN.GHNShippingOrder;
import com.orange.payload.response.GHNProvinceResponse;
import com.orange.payload.response.GHNShippingOrderResponse;
import com.orange.services.IShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ShippingService implements IShippingService {
    private static String GHN_TOKEN = "845cb13c-b64c-11ed-9a6e-422f22df4aa9";

    private final RestTemplate restTemplate;

    @Override
    public List<GHNProvince> getProvince() {
        HttpHeaders headers = getHttpHeaders();
        HttpEntity requestEntity = new HttpEntity(headers);
        try {
            return restTemplate.exchange
                            (
                                    "https://dev-online-gateway.ghn.vn/shiip/public-api/master-data/province",
                                    HttpMethod.GET,
                                    requestEntity,
                                    GHNProvinceResponse.class
                            )
                    .getBody()
                    .getData();
        } catch (RestClientException e) {
            throw new RuntimeException("Có gì đó sai sai =)))");
        }
    }

    @Override
    public GHNShippingOrderResponse createShippingOrder(GHNShippingOrder ghnShippingOrder) {
        HttpHeaders headers = getHttpHeaders();
        headers.set("ShopId", "121789");
        HttpEntity requestEntity = new HttpEntity(headers);
        try {
            return restTemplate.exchange
                            (
                                    "https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create",
                                    HttpMethod.POST,
                                    requestEntity,
                                    GHNShippingOrderResponse.class
                            )
                    .getBody();
        } catch (RestClientException e) {
            throw new RuntimeException("Có gì đó sai sai =)))");
        }
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("token", GHN_TOKEN);
        return headers;
    }
}
