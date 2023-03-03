package com.orange.repositories;

import com.orange.domain.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentTypeRepository extends JpaRepository<PaymentType, Long> {
}