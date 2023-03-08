package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.VariationOptionDTO;
import com.orange.domain.mapper.IVariationOptionMapper;
import com.orange.domain.model.VariationOption;
import com.orange.repositories.IVariationOptionRepository;
import com.orange.services.IVariationOpService;
import com.orange.services.IVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VariationOptionImpl implements IVariationOpService {
    private final IVariationOptionRepository variationOptionRepository;
    private final IVariationOptionMapper mapper;
    @Override
    public VariationOptionDTO create(VariationOptionDTO dto) {
        VariationOption variationOption = this.variationOptionRepository.save(mapper.toEntity(dto));
        return mapper.toDto(variationOption);
    }

    @Override
    public VariationOptionDTO update(VariationOptionDTO dto) {
        return null;
    }

    @Override
    public VariationOptionDTO delete(VariationOptionDTO dto) {
        return null;
    }

    @Override
    public Page<?> fillAll(Pageable pageable) {
        return null;
    }

    @Override
    public VariationOptionDTO findById(Long aLong) {
        return null;
    }
}
