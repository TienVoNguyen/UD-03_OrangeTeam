package com.orange.controller;

import com.orange.Utils.AccountUtils;
import com.orange.common.payload.Result;
import com.orange.domain.dto.CartDTO;
import com.orange.services.impl.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public Result<?> add(@RequestBody CartDTO cartDTO) {
        return Result.success("Thêm sản phẩm vào giỏ hàng thành công!", this.shoppingCartService.add(cartDTO));
    }
    @GetMapping("/update")
    public Result<?> update(@RequestParam Long id, @RequestParam Integer qty) {
        return Result.success("Cập nhật số lượng sản phẩm trong giỏ hàng thành công!", this.shoppingCartService.update(id, qty));
    }
    @GetMapping("")
    public Result<?> getCartItems() {
        return Result.success("Xem sản phẩm trong giỏ hàng thành công!", this.shoppingCartService.getAll(AccountUtils.getUsername()));
    }

    @GetMapping("/{id}")
    public Result<?> getCartItemById(
            @PathVariable Long id
    )  {
        if (this.shoppingCartService.findCartItemById(id) != null) {
            return Result.success("Xem chi tiết sản phẩm trong giỏ hàng thành công!", this.shoppingCartService.findCartItemById(id));
        }
            return Result.success("Sản phẩm trong giỏ hàng không tồn tại!",null);
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteCartItemById(
            @PathVariable Long id
    ) {
        return Result.success("Xóa sản phẩm trong giỏ hàng thành công!", this.shoppingCartService.deleteCartItemById(id));
    }


}
