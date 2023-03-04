package com.orange.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.orange.Utils.JsonUtils;
import com.orange.common.payload.Result;
import com.orange.domain.dto.CartDTO;
import com.orange.redis.RedisCacheService;
import com.orange.redis.RedisUtils;
import com.orange.services.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/test/cart")
@RequiredArgsConstructor
public class ShoppingCartController {
    private final RedisUtils redisUtils;

    @PostMapping()
    public Result<?> add(@RequestBody CartDTO cartDTO) {
        return Result.success("Thêm sản phẩm vào giỏ hàng thành công!", this.redisUtils.save(cartDTO));
    }
    @GetMapping("/{id}")
    public Result<?> getAllProducts(@PathVariable String id) {
        return Result.success("Xem sản phẩm trong giỏ hàng thành công!", this.redisUtils.getAll(id));
    }

    @GetMapping("/{key}/{id}")
    public Result<?> getProductById(
            @PathVariable String key,
            @PathVariable int id
    )  {
        if (this.redisUtils.findProductById(key, id) != null) {
            return Result.success("Xem chi tiết sản phẩm trong giỏ hàng thành công!", this.redisUtils.findProductById(key, id));
        }
            return Result.success("Sản phẩm trong giỏ hàng không tồn tại!",null);
    }

    @DeleteMapping("/{key}/{id}")
    public Result<?> deleteProductById(
            @PathVariable String key,
            @PathVariable int id
    ) {
        return Result.success("Xóa sản phẩm trong giỏ hàng thành công!", this.redisUtils.deleteCartProductbyId(key, id));
    }


}
