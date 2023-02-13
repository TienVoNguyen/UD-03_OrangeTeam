package com.orange.repositories;

import com.orange.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
