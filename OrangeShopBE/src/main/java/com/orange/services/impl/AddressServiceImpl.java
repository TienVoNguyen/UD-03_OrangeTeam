package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.AddressDTO;
import com.orange.domain.mapper.IAddressMapper;
import com.orange.domain.model.Address;
import com.orange.repositories.IAddressRepository;
import com.orange.services.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {

    private final IAddressRepository addressRepository;
    private final IAddressMapper addressMapper;

    @Override
    public AddressDTO create(AddressDTO dto) {
        return null;
    }

    @Override
    public AddressDTO update(AddressDTO dto) {
        return null;
    }

    @Override
    public AddressDTO delete(AddressDTO dto) {
        return null;
    }

    @Override
    public Page<?> fillAll(Pageable pageable) {
        org.springframework.data.domain.Page<Address> result = this.addressRepository.findAll(pageable);
        List<AddressDTO> viewDTOList = result.getContent()
                .stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
        return Page.of(result.getSize(), result.getNumber(), result.getTotalPages(), Math.toIntExact(result.getTotalElements()), viewDTOList);
    }

    @Override
    public Page<?> fillAddressByUser(Long userId, Pageable pageable) {

        org.springframework.data.domain.Page<Address> result = this.addressRepository.findAddressByUser(userId, pageable);
        List<AddressDTO> viewDTOList = result.getContent()
                .stream()
                .map(addressMapper::toDto)
                .collect(Collectors.toList());
        return Page.of(result.getSize(), result.getNumber(), result.getTotalPages(), Math.toIntExact(result.getTotalElements()), viewDTOList);
    }

    @Override
    public AddressDTO findById(Long aLong) {
        return null;
    }
}
