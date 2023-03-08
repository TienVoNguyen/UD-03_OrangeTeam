package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.VariationDTO;
import com.orange.domain.mapper.IVariationMapper;
import com.orange.domain.model.Variation;
import com.orange.exception.EntityNotFoundException;
import com.orange.repositories.IVariationRepository;
import com.orange.services.IVariationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VariationServiceImpl implements IVariationService {
    private final IVariationRepository variationRepository;
    private final IVariationMapper variationMapper;

    @Override
    public VariationDTO create(VariationDTO dto) {
        Variation result = this.variationRepository.save(variationMapper.toEntity(dto));
        return variationMapper.toDto(result);
    }

    @Override
    public VariationDTO update(VariationDTO dto) {
        return null;
    }

    @Override
    public VariationDTO delete(VariationDTO dto) {
        return null;
    }

    @Override
    public Page<?> fillAll(Pageable pageable) {
        org.springframework.data.domain.Page<Variation> result = this.variationRepository.findAll(pageable);
        List<VariationDTO> variationDTOS = result.getContent()
                .stream()
                .map(c -> this.variationMapper.toDto(c))
                .collect(Collectors.toList());
        return Page.of(result.getSize(), result.getNumber(),result.getTotalPages(), Math.toIntExact(result.getTotalElements()), variationDTOS);

    }

    @Override
    public VariationDTO findById(Long aLong) {
        Variation variation = this.variationRepository.findById(aLong).orElse(null);
        if(variation == null) throw new EntityNotFoundException("Can't found your variation id");
        return variationMapper.toDto(variation);
    }
}
