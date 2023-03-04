package com.orange.controller;


import com.orange.common.payload.Page;
import com.orange.common.payload.Result;
import com.orange.services.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/test/address")
@RequiredArgsConstructor
public class AddressController {

    private final IAddressService iAddressService;

    @GetMapping("")
    public Result<?> getAllAddress(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size) {
        Page<?> pages = this.iAddressService.fillAll(PageRequest.of(page, size));
        return Result.result(HttpStatus.OK.value(), "Lấy dữ liệu adddress thành công!", pages);
    }

    @GetMapping("/by-user")
    public Result<?> getAddressByUser(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size,
                                      @RequestParam Long userID) {
        Page<?> pages = this.iAddressService.fillAddressByUser(userID, PageRequest.of(page, size));
        return Result.result(HttpStatus.OK.value(), "Lấy dữ liệu adddress thành công!", pages);
    }
}
