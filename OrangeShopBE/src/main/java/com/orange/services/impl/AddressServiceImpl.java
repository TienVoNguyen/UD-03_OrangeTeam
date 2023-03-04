package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.AddressDTO;
import com.orange.domain.mapper.IAddressMapper;
import com.orange.domain.model.*;
import com.orange.exception.EntityType;
import com.orange.exception.ExceptionType;
import com.orange.exception.GlobalException;
import com.orange.repositories.*;
import com.orange.services.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressServiceImpl implements IAddressService {

    private final IAddressRepository addressRepository;
    private final IAddressMapper addressMapper;
    private final IVillageRepository villageRepository;
    private final IDistrictRepository districtRepository;
    private final ICityRepository cityRepository;
    private final IUserAddressRepository userAddressRepository;

    @Override
    public AddressDTO create(AddressDTO dto) {
        if(findByAddressDTO(dto).isPresent()) {
            GlobalException.throwException(EntityType.product, ExceptionType.ENTITY_ALREADY_EXIST, "Address already exists");
        };

        Address address = checkVillageDistrictCity(dto);

        address = addressRepository.save(address);
        return addressMapper.toDto(address);
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
    public List<?> fillAddressByUser(String username) {

        List<Address> result = this.addressRepository.findAddressByUser(username);
        List<AddressDTO> viewDTOList = addressMapper.toDtoList(result);
        return viewDTOList;
    }

    @Override
    public AddressDTO findById(Long id) {
        Address address = getAddressById(id);
        return addressMapper.toDto(address);
    }

    @Override
    public AddressDTO addUserAddress(User user, AddressDTO addressDTO) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUser(user);

        Optional<Address> addressOptional = findByAddressDTO(addressDTO);
        if (addressOptional.isPresent()) {
            userAddress.setAddress(addressOptional.get());
        } else {
            AddressDTO newAddressDTO = create(addressDTO);
            userAddress.setAddress(addressMapper.toEntity(newAddressDTO));
        }
        Optional<UserAddress> userAddressOptional = this.userAddressRepository.findByUserAndAddress(userAddress.getUser(), userAddress.getAddress());
        if (userAddressOptional.isPresent()) {
            GlobalException.throwException(EntityType.product, ExceptionType.ENTITY_ALREADY_EXIST, "Address đã tồn tại!");
        }
        this.userAddressRepository.save(userAddress);
        return addressMapper.toDto(userAddress.getAddress());
    }

    private Address getAddressById(Long id) {
        return this.addressRepository.findById(id)
                .orElseThrow(() -> GlobalException.throwException(EntityType.product, ExceptionType.ENTITY_NOT_FOUND, "Không tìm thấy Address!"));
    }


    private Optional<Address> findByAddressDTO(AddressDTO dto) {
        Long id = dto.getId();
        if (id != null) {
            return addressRepository.findById(id);
        } else {
            String addressLine1 = dto.getAddressLine1();
            String addressLine2 = dto.getAddressLine2();
            Long villageId = dto.getVillage().getId();
            return addressRepository.findByAddressLine1AndAddressLine2AndVillageId(addressLine1, addressLine2, villageId);
        }
    }

    private Address checkVillageDistrictCity(AddressDTO dto) {

        Address address = addressMapper.toEntity(dto);

        Village village = address.getVillage();
        District district = village.getDistrict();
        City city = district.getCity();

        // Kiểm tra xem City đã tồn tại hay chưa
        Optional<City> cityOptional = cityRepository.findById(city.getId());
        if (cityOptional.isEmpty()) {
            // Nếu City chưa tồn tại, tạo mới City
            city = cityRepository.save(city);
        } else {
            // Nếu City đã tồn tại, lấy thông tin của nó
            city = cityOptional.get();
        }

        // Cập nhật lại District với thông tin mới nhất của City
        district.setCity(city);

        // Kiểm tra xem District đã tồn tại hay chưa
        Optional<District> districtOptional = districtRepository.findById(district.getId());
        if (districtOptional.isEmpty()) {
            // Nếu District chưa tồn tại, tạo mới District
            district = districtRepository.save(district);
        } else {
            // Nếu District đã tồn tại, lấy thông tin của nó
            district = districtOptional.get();
        }

        // Cập nhật lại Village với thông tin mới nhất của District
        village.setDistrict(district);

        // Kiểm tra xem Village đã tồn tại hay chưa
        Optional<Village> villageOptional = villageRepository.findById(village.getId());
        if (villageOptional.isEmpty()) {
            // Nếu Village chưa tồn tại, tạo mới Village
            village = villageRepository.save(village);
        } else {
            // Nếu Village đã tồn tại, lấy thông tin của nó
            village = villageOptional.get();
        }

        // Cập nhật lại địa chỉ với thông tin mới nhất của Village
        address.setVillage(village);

        /*// Kiểm tra xem Address đã tồn tại hay chưa
        Optional<Address> addressOptional = addressRepository.findById(address.getId());
        if (addressOptional.isEmpty()) {
            // Nếu Address chưa tồn tại, tạo mới Address
            address = addressRepository.save(address);
        } else {
            // Nếu Address đã tồn tại, lấy thông tin của nó
            address = addressOptional.get();
        }*/
        return address;
    }
}
