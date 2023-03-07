package com.orange.controller;

import com.orange.common.payload.Page;
import com.orange.common.payload.Result;
import com.orange.domain.dto.ProductDTO;
import com.orange.exception.EntityIsEmptyException;
import com.orange.services.IProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
    private final IProductService productService;
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping("")
    private Result<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size)
    {
        Page<?> pages = (Page<?>) this.productService.fillAll(PageRequest.of(page, size));
        return Result.result(HttpStatus.OK.value(), "Lấy dữ liệu thành công!", pages);
    }

    @GetMapping("/product-detail")
    public Result<?> getProductById(@RequestParam(value = "id", defaultValue = "0")Optional<Long> id) {
        if(!id.isPresent()) {
            throw new EntityIsEmptyException("Id không tồn tại!");
        }
        ProductDTO productDTO = this.productService.findById(id.get());
        return Result.result(HttpStatus.OK.value(), "Lấy chi tiết sản phẩm thành công!", productDTO);
    }

    @PostMapping("/create-product")
    public Result<?> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO result = this.productService.create(productDTO);
        return Result.result(HttpStatus.OK.value(), "Thêm sản phẩm mới thành công!", result);
    }

    @PutMapping("/update-product/{id}")
    public Result<?> updateProduct(
            @RequestParam Optional<Long> id,
            @RequestBody ProductDTO productDTO)
    {
        if(!id.isPresent()) {
            throw new EntityIsEmptyException("Id không tồn tại!");
        }
        ProductDTO result = this.productService.create(productDTO);
        return Result.result(HttpStatus.OK.value(), "Cập nhập sản phẩm thành công!", result);
    }
}
