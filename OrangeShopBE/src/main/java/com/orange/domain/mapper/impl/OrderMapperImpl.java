package com.orange.domain.mapper.impl;

import com.orange.domain.mapper.IOrderDetailMapper;
import com.orange.domain.model.Order;
import com.orange.domain.mapper.IAddressMapper;
import com.orange.domain.mapper.IOrderMapper;
import com.orange.domain.mapper.IUserPaymentMethodMapper;
import com.orange.domain.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapperImpl implements IOrderMapper {

    private final ModelMapper modelMapper;
    private final IUserPaymentMethodMapper userPaymentMethodMapper;
    private final IAddressMapper addressMapper;
    private final IOrderDetailMapper orderDetailMapper;

    @Override
    public Order toEntity(OrderDTO dto) {
        Order entity = modelMapper.map(dto, Order.class);
        entity.setAddress(addressMapper.toEntity(dto.getAddress()));
        entity.setUserPaymentMethod(userPaymentMethodMapper.toEntity(dto.getUserPaymentMethod()));
        entity.setOrderDetails(orderDetailMapper.toEntitySet(dto.getOrderDetails()));
        return entity;
    }

    @Override
    public OrderDTO toDto(Order entity) {
        OrderDTO dto = modelMapper.map(entity, OrderDTO.class);
        dto.setAddress(addressMapper.toDto(entity.getAddress()));
        dto.setUserPaymentMethod(userPaymentMethodMapper.toDto(entity.getUserPaymentMethod()));
        dto.setOrderDetails(orderDetailMapper.toDtoSet(entity.getOrderDetails()));
        return dto;
    }
}
