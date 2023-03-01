package com.orange.controller;

import com.orange.common.payload.Result;
import com.orange.domain.dto.OrderDTO;
import com.orange.exception.EntityIsEmptyException;
import com.orange.services.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/test/order")
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;

    @GetMapping("")
    public Result<?> getAllOrders(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "20") int size){
        Page<?> pages = this.orderService.fillAll(PageRequest.of(page, size));
        return Result.result(HttpStatus.OK.value(), "Lấy danh sách các order thành công", pages);
    }

    @GetMapping("/order-detail")
    public Result<?> getOrderById(@RequestParam(value = "id", defaultValue = "0") Optional<Long> id){
        if (id.isPresent()) {
            OrderDTO orderDTO = this.orderService.findById(id.get());
            return Result.result(HttpStatus.OK.value(), "Lấy chi tiết order thành công", orderDTO);
        } else {
            throw new EntityIsEmptyException("Không có id!");
        }
    }

    @PostMapping("/create-order")
    public Result<?> createOrder(@RequestBody OrderDTO orderDTO){
        OrderDTO result = this.orderService.create(orderDTO);
        return Result.result(HttpStatus.OK.value(), "Tạo order thành công", result);
    }
}
