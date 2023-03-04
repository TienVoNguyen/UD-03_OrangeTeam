package com.orange.repositories;

import com.orange.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IAddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByAddressLine1AndAddressLine2AndVillageId(String addressLine1, String addressLine2, Long villageId);

    @Query("FROM UserAddress ua where ua.user.username =:username")
    List<Address> findAddressByUser(@Param("username")String username);
}