package com.orange.service.impl;

import com.orange.domain.model.ProductDetail;
import com.orange.repositories.IProductDetailRepository;
import com.orange.service.IProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetalServiceImpl implements IProductDetailService {
    @Autowired
    IProductDetailRepository iProductDetailRepository;

    @Override
    public List<ProductDetail> findAll() {
        return iProductDetailRepository.findAll();
    }

    @Override
    public ProductDetail findById(Long id) {
        return iProductDetailRepository.findById(id).orElse(null);
    }

}
