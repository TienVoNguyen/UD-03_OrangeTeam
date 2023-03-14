package com.orange.payload.request;

import lombok.Data;

@Data
public class CartUpdateRequest {
    private Long id;
    private Integer qty;
}
