package com.orange.controller;

import com.orange.common.payload.Page;
import com.orange.common.payload.Result;
import com.orange.services.IPaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/test/payment-type")
@RequiredArgsConstructor
public class PaymentTypeController {

    private final IPaymentTypeService paymentTypeService;

    @GetMapping("")
    public Result<?> getAllPaymentType(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "20") int size){
        Page<?> pages = this.paymentTypeService.fillAll(PageRequest.of(page, size));
        return Result.result(HttpStatus.OK.value(), "Lấy dữ liệu payment type thành công!", pages);
    }
}
