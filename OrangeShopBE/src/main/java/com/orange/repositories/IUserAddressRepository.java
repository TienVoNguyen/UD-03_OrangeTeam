package com.orange.repositories;

import com.orange.domain.model.Address;
import com.orange.domain.model.User;
import com.orange.domain.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserAddressRepository extends JpaRepository<UserAddress, Long> {
    Optional<UserAddress> findByUserAndAddress(User user, Address address);
    Optional<UserAddress> findByUser_IdAndAddress_Id(Long id, Long id1);
}