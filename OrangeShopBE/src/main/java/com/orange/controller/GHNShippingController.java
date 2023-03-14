package com.orange.controller;

import com.orange.common.payload.Result;
import com.orange.domain.model.ghn.GHNProvince;
import com.orange.payload.request.ghn.GHNCalculateFeeRequest;
import com.orange.payload.response.GHNCalculateFeeResponse;
import com.orange.services.IShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/test/shipping")
@RequiredArgsConstructor
public class GHNShippingController {

    private final IShippingService shippingService;

    @GetMapping("/province")
    public Result<?> getProvince(){
        List<GHNProvince> data = shippingService.getProvince();
        return Result.result(HttpStatus.OK.value(), "xong", data);
    }

    @PostMapping("/calculate-fee")
    public Result<?> calculateFee(@RequestBody GHNCalculateFeeRequest calculateFeeRequest){
        GHNCalculateFeeResponse data = shippingService.calculateFee(calculateFeeRequest);
        return Result.result(HttpStatus.OK.value(), "xong", data);
    }
}
