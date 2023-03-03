package com.orange.services;

import com.orange.domain.dto.OrderDTO;
import com.orange.payload.request.UpdateOrderStatus;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderService extends BaseService<OrderDTO, Long> {

    @Transactional
    OrderDTO updateOrderStatus(UpdateOrderStatus orderStatus);
}
