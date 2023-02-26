package com.orange.domain.mapper.impl;

import com.orange.domain.mapper.IProductDetailMapper;
import com.orange.domain.model.Order;
import com.orange.domain.model.OrderDetail;
import com.orange.domain.mapper.IOrderDetailMapper;
import com.orange.domain.dto.OrderDetailDTO;
import com.orange.domain.model.ProductDetail;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDetailMapperImpl implements IOrderDetailMapper {

    private final ModelMapper modelMapper;
    private final IProductDetailMapper productDetailMapper;

    @Override
    public OrderDetail toEntity(OrderDetailDTO dto) {
        OrderDetail entity = modelMapper.map(dto, OrderDetail.class);
        entity.setOrder(new Order(dto.getOrderId()));
        entity.setProductDetail(new ProductDetail(dto.getProductDetailId()));
        return entity;
    }

    @Override
    public OrderDetailDTO toDto(OrderDetail entity) {
        OrderDetailDTO dto = modelMapper.map(entity, OrderDetailDTO.class);
        dto.setOrderId(entity.getOrder().getId());
        dto.setProductDetailId(entity.getProductDetail().getId());
        return dto;
    }
}
