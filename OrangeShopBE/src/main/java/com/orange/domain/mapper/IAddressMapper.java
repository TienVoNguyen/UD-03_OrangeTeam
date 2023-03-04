package com.orange.domain.mapper;

import com.orange.domain.model.Address;
import com.orange.domain.dto.AddressDTO;
import org.springframework.stereotype.Component;

@Component
public interface IAddressMapper extends IEntityMapper<AddressDTO, Address> {
}
