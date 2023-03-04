package com.orange.repositories;

import com.orange.domain.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAddressRepository extends JpaRepository<Address, Long> {

    @Query("FROM UserAddress ua where ua.user.id =:userId")
    Page<Address> findAddressByUser(@Param("id")Long userId, Pageable pageable);
}