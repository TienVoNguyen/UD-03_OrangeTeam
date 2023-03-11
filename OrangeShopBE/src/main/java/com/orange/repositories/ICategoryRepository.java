package com.orange.repositories;

import com.orange.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICategoryRepository extends JpaRepository<Category, Long> {
    Page<Category> findAllByStatusIsTrue(Pageable pageable);
}