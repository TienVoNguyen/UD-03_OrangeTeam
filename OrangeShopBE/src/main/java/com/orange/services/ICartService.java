package com.orange.services;

import com.orange.domain.dto.CartDTO;
import com.orange.redis.RedisQueue;

public interface ICartService {

    void add(CartDTO cartDTO);

    abstract void clear();
}
