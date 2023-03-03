package com.orange.domain.mapper.impl;

import com.orange.domain.dto.OrderViewDTO;
import com.orange.domain.mapper.IAddressMapper;
import com.orange.domain.mapper.IOrderDetailViewMapper;
import com.orange.domain.mapper.IOrderViewMapper;
import com.orange.domain.mapper.IUserPaymentMethodMapper;
import com.orange.domain.model.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderViewMapperImpl implements IOrderViewMapper {

    private final ModelMapper modelMapper;
    private final IUserPaymentMethodMapper userPaymentMethodMapper;
    private final IAddressMapper addressMapper;
    private final IOrderDetailViewMapper orderDetailViewMapper;

    @Override
    public Order toEntity(OrderViewDTO dto) {
        Order entity = modelMapper.map(dto, Order.class);
        entity.setOrderDetails(orderDetailViewMapper.toEntitySet(dto.getOrderDetailViews()));
        entity.setAddress(addressMapper.toEntity(dto.getAddress()));
//        entity.setUserPaymentMethod(userPaymentMethodMapper.toEntity(dto.get));

        return entity;
    }

    @Override
    public OrderViewDTO toDto(Order entity) {
        OrderViewDTO dto = modelMapper.map(entity, OrderViewDTO.class);
        dto.setOrderDetailViews(orderDetailViewMapper.toDtoSet(entity.getOrderDetails()));
        dto.setAddress(addressMapper.toDto(entity.getAddress()));
        dto.setProductQuantity(entity.getOrderDetails().size());

        return dto;
    }
}
