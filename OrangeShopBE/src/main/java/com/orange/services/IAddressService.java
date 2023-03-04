package com.orange.services;

import com.orange.domain.dto.AddressDTO;
import com.orange.domain.model.User;

import java.util.List;

public interface IAddressService extends BaseService<AddressDTO, Long>{
    List<?> fillAddressByUser(String username);

    AddressDTO addUserAddress(User user, AddressDTO addressDTO);
}
