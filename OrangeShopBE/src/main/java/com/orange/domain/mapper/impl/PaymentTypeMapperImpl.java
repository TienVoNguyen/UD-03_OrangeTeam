package com.orange.domain.mapper.impl;

import com.orange.domain.dto.PaymentTypeDTO;
import com.orange.domain.mapper.IPaymentTypeMapper;
import com.orange.domain.model.PaymentType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentTypeMapperImpl implements IPaymentTypeMapper {

    private final ModelMapper mapper;

    @Override
    public PaymentType toEntity(PaymentTypeDTO dto) {
        PaymentType entity = mapper.map(dto, PaymentType.class);
        return entity;
    }

    @Override
    public PaymentTypeDTO toDto(PaymentType entity) {
        PaymentTypeDTO dto = mapper.map(entity, PaymentTypeDTO.class);
        return dto;
    }
}
