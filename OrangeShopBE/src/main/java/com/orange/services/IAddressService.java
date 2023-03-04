package com.orange.services;

import com.orange.common.payload.Page;
import com.orange.domain.dto.AddressDTO;
import org.springframework.data.domain.Pageable;

public interface IAddressService extends BaseService<AddressDTO, Long>{
    Page<?> fillAddressByUser(Long userId, Pageable pageable);
}
