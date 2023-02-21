package com.orange.service.impl;

import com.orange.repositories.IProductRepository;
import com.orange.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductRepository productRepository;

    @Override
    public Page findAll(String pageParam, String limitParam) {
    int page = pageParam == null || Integer.parseInt(pageParam) == 1? 1 : Integer.parseInt(pageParam);
    int limit = limitParam == null ? 25 : Integer.parseInt(limitParam);
        Pageable pageable = PageRequest.of(page, limit);
    Page pageData = this.productRepository.findAll(pageable);
    return pageData;
    }
}
