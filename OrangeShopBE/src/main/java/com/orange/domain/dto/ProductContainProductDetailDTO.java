package com.orange.domain.dto;

import lombok.Data;

import java.util.Set;

@Data @Deprecated
public class ProductContainProductDetailDTO {
    private ProductDTO product;
    private Set<ProductDetailDTO> productDetails;
}
