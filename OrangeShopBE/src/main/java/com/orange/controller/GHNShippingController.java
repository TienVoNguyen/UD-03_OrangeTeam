package com.orange.controller;

import com.orange.common.payload.Result;
import com.orange.domain.model.GHN.GHNProvince;
import com.orange.services.IShippingService;
import com.orange.services.impl.ShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/test/shipping")
@RequiredArgsConstructor
public class GHNShippingController {

    private final IShippingService shippingService;

    @GetMapping
    public Result<?> getProvince(){
        List<GHNProvince> data = shippingService.getProvince();
        return Result.result(HttpStatus.OK.value(), "xong", data);
    }
}
