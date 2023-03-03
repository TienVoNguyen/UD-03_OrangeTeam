package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.PaymentTypeDTO;
import com.orange.domain.mapper.IPaymentTypeMapper;
import com.orange.domain.model.PaymentType;
import com.orange.repositories.IPaymentTypeRepository;
import com.orange.services.IPaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentTypeService implements IPaymentTypeService {

    private final IPaymentTypeRepository paymentTypeRepository;
    private final IPaymentTypeMapper paymentTypeMapper;

    @Override
    public PaymentTypeDTO create(PaymentTypeDTO dto) {
        return null;
    }

    @Override
    public PaymentTypeDTO update(PaymentTypeDTO dto) {
        return null;
    }

    @Override
    public PaymentTypeDTO delete(PaymentTypeDTO dto) {
        return null;
    }

    @Override
    public Page<?> fillAll(Pageable pageable) {
        org.springframework.data.domain.Page<PaymentType> result = this.paymentTypeRepository.findAll(pageable);
        List<PaymentTypeDTO> viewDTOList = result.getContent()
                .stream()
                .map(paymentTypeMapper::toDto)
                .collect(Collectors.toList());
        int totalPages = result.getTotalPages();
        return Page.of(totalPages, result.getNumber(),totalPages, Math.toIntExact(result.getTotalElements()), viewDTOList);
    }

    @Override
    public PaymentTypeDTO findById(Long aLong) {
        return null;
    }
}
