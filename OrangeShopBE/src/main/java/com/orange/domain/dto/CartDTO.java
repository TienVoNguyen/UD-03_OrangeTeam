package com.orange.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@RedisHash("CartDTO")
public class CartDTO implements Serializable {
    private Long productDetailId;
    private String productName;
    private String image;
    private String color;
    private String size;
    private Integer quantity;
    private Double price;
}
