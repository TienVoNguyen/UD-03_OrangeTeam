package com.orange.repositories;

import com.orange.domain.model.Address;
import com.orange.domain.model.User;
import com.orange.domain.model.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserAddressRepository extends JpaRepository<UserAddress, Long> {
    List<UserAddress> findUserAddressesByUser_Id(Long userId);
    Optional<UserAddress> findFirstByUser_IdAndAddress_Id(@Param("uid") Long uid, @Param("aid") Long aid);
}