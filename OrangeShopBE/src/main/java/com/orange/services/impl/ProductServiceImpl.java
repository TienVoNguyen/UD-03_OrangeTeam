package com.orange.services.impl;

import com.orange.domain.dto.ProductDTO;
import com.orange.domain.dto.ProductDetailDTO;
import com.orange.domain.mapper.IProductDetailMapper;
import com.orange.domain.mapper.IProductMapper;
import com.orange.domain.model.Product;
import com.orange.domain.model.ProductDetail;
import com.orange.exception.EntityNotFoundException;
import com.orange.repositories.IProductDetailRepository;
import com.orange.repositories.IProductRepository;
import com.orange.services.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final IProductDetailRepository productDetailRepository;
    private final IProductMapper productMapper;
    private final IProductDetailMapper productDetailMapper;

    @Override
    public ProductDTO create(ProductDTO dto) {
        Product result = this.productRepository.save(productMapper.toEntity(dto));
        Set<ProductDetail> productDetails = result.getProductDetails();
        productDetails.forEach(p -> p.setProduct(result));
        productDetailRepository.saveAll(productDetails);
        result.setProductDetails(productDetails);
        return productMapper.toDto(result);
    }

    @Override
    public ProductDTO update(ProductDTO dto) {
        return null;
    }

    @Override
    public ProductDTO delete(ProductDTO dto) {
        return null;
    }

    @Override
    public com.orange.common.payload.Page<?> fillAll(Pageable pageable) {
        Page<Product> result = this.productRepository.findAll(pageable);
        int totalPages = result.getTotalPages();
        List<Product> productList = result.toList();
        List<ProductDTO> DTOList = productMapper.toDtoList(productList);
        com.orange.common.payload.Page pageProduct = new com.orange.common.payload.Page();
        pageProduct.setResult(DTOList);
        pageProduct.setTotalPages(totalPages);
        pageProduct.setTotalItems((int) result.getTotalElements());
        pageProduct.setPageSize(result.getSize());
        pageProduct.setPageNumber(result.getNumber());
        return pageProduct;
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = this.productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new EntityNotFoundException("Product not found!");
        }
        return productMapper.toDto(product);
    }
}
