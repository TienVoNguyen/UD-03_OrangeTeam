package com.orange.service;

import com.orange.domain.model.ProductDetail;

import java.util.List;

public interface IProductDetailService {
    List<ProductDetail> findAll();

    abstract ProductDetail findById(Long id);
}
