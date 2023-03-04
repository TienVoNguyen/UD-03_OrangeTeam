package com.orange.domain.dto;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Data
@RedisHash("CartDTO")
public class CartDTO implements Serializable {

    @Id
    private int cartId;
    private int userId;
    private String productName;
    private String categoryid;
    private String color;
    private String size;
    private List<ProductDetailCartDTO> productDetailCartDTOList;
    private double quantity;
}
