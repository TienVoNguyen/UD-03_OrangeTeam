package com.orange.payload.request;

import lombok.Data;

@Data
public class UpdateOrderStatus {
    private Long orderId;
    private Integer orderStatusId;
}
