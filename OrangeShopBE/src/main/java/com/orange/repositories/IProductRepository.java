package com.orange.repositories;

import com.orange.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByCategoryIdAndStatusIsTrue(Long cid, Pageable pageable);
    Page<Product> findAllByStatusIsTrueAndCategory_Id(Long cid, Pageable pageable);
    Page<Product> findAllByStatusIsTrue(Pageable pageable);
}