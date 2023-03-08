package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.VariationDTO;
import com.orange.domain.dto.VariationOptionDTO;
import com.orange.domain.mapper.IVariationOptionMapper;
import com.orange.domain.model.Variation;
import com.orange.domain.model.VariationOption;
import com.orange.repositories.IVariationOptionRepository;
import com.orange.services.IVariationOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VariationOptionServiceImpl implements IVariationOptionService {
    private final IVariationOptionRepository variationOptionRepository;
    private final IVariationOptionMapper mapper;

    @Override
    public VariationOptionDTO create(VariationOptionDTO dto) {
        VariationOption result = this.variationOptionRepository.save(mapper.toEntity(dto));
        return mapper.toDto(result);
    }

    @Override
    public VariationOptionDTO update(VariationOptionDTO dto) {
        VariationOption existingVariationOption = variationOptionRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Variation option with id " + dto.getId() + " not found"));
        VariationOption updatedVariationOption = variationOptionRepository.save(existingVariationOption);
        return mapper.toDto(updatedVariationOption);
    }

    @Override
    public VariationOptionDTO delete(VariationOptionDTO dto) {
        return null;
    }

    @Override
    public Page<?> fillAll(Pageable pageable) {
      org.springframework.data.domain.Page<VariationOption> result = this.variationOptionRepository.findAll(pageable);
        List<VariationOptionDTO> variationOptionDTOS = result.getContent()
                .stream()
                .map(c -> this.mapper.toDto(c))
                .collect(Collectors.toList());
        return Page.of(result.getSize(), result.getNumber(),result.getTotalPages(), Math.toIntExact(result.getTotalElements()), variationOptionDTOS);

    }

    @Override
    public VariationOptionDTO findById(Long aLong) {
        VariationOption existingVariationOption = variationOptionRepository.findById(aLong)
                .orElseThrow(() -> new IllegalArgumentException("Variation option with id " + aLong + " not found"));
        return mapper.toDto(existingVariationOption);
    }
}
