package com.orange.services.impl;

import com.orange.domain.dto.CartDTO;
import com.orange.redis.RedisQueue;
import com.orange.services.ICartService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements ICartService{
    private RedisTemplate redisTemplate;
    private final RedisQueue<CartDTO> redisQueue = new RedisQueue<>(redisTemplate, "shopping_cart", CartDTO.class);

    @Override
    public void add(CartDTO cartDTO) {
        this.redisQueue.add(cartDTO);
    }

    @Override
    public void clear() {
        this.redisQueue.remove();
    }
}
