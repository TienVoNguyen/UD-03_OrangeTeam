package com.orange.repositories;

import com.orange.domain.model.Village;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVillageRepository extends JpaRepository<Village, Long> {
}