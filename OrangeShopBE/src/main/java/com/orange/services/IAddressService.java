package com.orange.services;

import com.orange.domain.dto.AddressDTO;
import com.orange.domain.model.User;

import java.util.List;

public interface IAddressService extends BaseService<AddressDTO, Long>{
    List<AddressDTO> fillAddressByUser(Long userId);

    AddressDTO addUserAddress(User user, AddressDTO addressDTO);

    AddressDTO setDefaultAddressForUser(User user, Long addressId);
}
