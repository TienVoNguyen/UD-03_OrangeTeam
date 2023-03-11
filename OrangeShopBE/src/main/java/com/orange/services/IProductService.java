package com.orange.services;

import com.orange.domain.dto.ProductDTO;
import org.springframework.data.domain.Pageable;

public interface IProductService extends BaseService<ProductDTO, Long> {
    com.orange.common.payload.Page<?> findProductsByCategory(Pageable pageable, Long cid);
}
