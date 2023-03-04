package com.orange.repositories;

import com.orange.domain.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDistrictRepository extends JpaRepository<District, Long> {
}