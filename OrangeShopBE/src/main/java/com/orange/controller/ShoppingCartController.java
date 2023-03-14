package com.orange.controller;

import com.orange.Utils.AccountUtils;
import com.orange.common.payload.Result;
import com.orange.domain.dto.CartDTO;
import com.orange.exception.EntityType;
import com.orange.exception.ExceptionType;
import com.orange.exception.GlobalException;
import com.orange.payload.request.CartUpdateRequest;
import com.orange.services.impl.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @PutMapping("/update")
    public Result<?> update(@RequestBody Optional<CartUpdateRequest> cart) {
        if (cart.isPresent()) {
            Long id = cart.get().getId();
            Integer qty = cart.get().getQty();
            return Result.success("Cập nhật số lượng sản phẩm trong giỏ hàng thành công!", this.shoppingCartService.update(id, qty));
        } else {
            throw GlobalException.throwException(EntityType.product, ExceptionType.ENTITY_NOT_FOUND, "Thiếu tham số truyền vào");
        }
    }

    @GetMapping("")
    public Result<?> getCartItems() {
        return Result.success("Xem sản phẩm trong giỏ hàng thành công!", this.shoppingCartService.getAll(AccountUtils.getUsername()));
    }

    @GetMapping("/{id}")
    public Result<?> getCartItemById(
            @PathVariable Long id
    ) {
        if (this.shoppingCartService.findCartItemById(id) != null) {
            return Result.success("Xem chi tiết sản phẩm trong giỏ hàng thành công!", this.shoppingCartService.findCartItemById(id));
        }
        return Result.success("Sản phẩm trong giỏ hàng không tồn tại!", null);
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteCartItemById(
            @PathVariable Long id
    ) {
        return Result.success("Xóa sản phẩm trong giỏ hàng thành công!", this.shoppingCartService.deleteCartItemById(id));
    }


}
