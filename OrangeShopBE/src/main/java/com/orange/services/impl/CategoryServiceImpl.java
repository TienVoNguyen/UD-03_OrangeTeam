package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.CategoryDTO;
import com.orange.domain.model.Category;
import com.orange.payload.response.CategoryResponse;
import com.orange.repositories.ICategoryRepository;
import com.orange.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final ModelMapper mapper;

    @Override
    public CategoryDTO create(CategoryDTO dto) {
        return null;
    }

    @Override
    public CategoryDTO update(CategoryDTO dto) {
        return null;
    }

    @Override
    public CategoryDTO delete(CategoryDTO dto) {
        return null;
    }

    @Override
    public Page<?> fillAll(Pageable pageable) {
        org.springframework.data.domain.Page<Category> result = this.categoryRepository.findAll(pageable);
        List<CategoryResponse> categoryResponses = result.getContent()
                .stream()
                .map(c -> this.mapper.map(c, CategoryResponse.class))
                .collect(Collectors.toList());
        return Page.of(result.getSize(), result.getNumber(),result.getTotalPages(), Math.toIntExact(result.getTotalElements()), categoryResponses);
    }

    @Override
    public CategoryDTO findById(Long aLong) {
        return null;
    }
}
