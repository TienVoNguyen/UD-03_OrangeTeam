package com.orange.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.orange.domain.dto.AddressDTO;
import com.orange.domain.mapper.IAddressMapper;
import com.orange.domain.model.Address;
import com.orange.domain.model.City;
import com.orange.domain.model.District;
import com.orange.domain.model.User;
import com.orange.domain.model.UserAddress;
import com.orange.domain.model.Village;
import com.orange.repositories.IAddressRepository;
import com.orange.repositories.ICityRepository;
import com.orange.repositories.IDistrictRepository;
import com.orange.repositories.IUserAddressRepository;
import com.orange.repositories.IVillageRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AddressServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AddressServiceImplTest {
    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @MockBean
    private IAddressMapper iAddressMapper;

    @MockBean
    private IAddressRepository iAddressRepository;

    @MockBean
    private ICityRepository iCityRepository;

    @MockBean
    private IDistrictRepository iDistrictRepository;

    @MockBean
    private IUserAddressRepository iUserAddressRepository;

    @MockBean
    private IVillageRepository iVillageRepository;

    /**
     * Method under test: {@link AddressServiceImpl#create(AddressDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreate() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.orange.config.MessageProperties.getConfigValue(String)" because "com.orange.exception.GlobalException.propertiesConfig" is null
        //       at com.orange.exception.GlobalException.format(GlobalException.java:94)
        //       at com.orange.exception.GlobalException.throwException(GlobalException.java:84)
        //       at com.orange.exception.GlobalException.throwException(GlobalException.java:41)
        //       at com.orange.services.impl.AddressServiceImpl.create(AddressServiceImpl.java:36)
        //   See https://diff.blue/R013 to resolve this issue.

        City city = new City();
        city.setDistricts(new HashSet<>());
        city.setId(123L);
        city.setName("Name");
        city.setStatus(true);

        District district = new District();
        district.setCity(city);
        district.setId(123L);
        district.setName("Name");
        district.setStatus(true);
        district.setVillages(new HashSet<>());

        Village village = new Village();
        village.setAddresses(new HashSet<>());
        village.setDistrict(district);
        village.setId(123L);
        village.setName("Name");

        Address address = new Address();
        address.setAddressLine1("42 Main St");
        address.setAddressLine2("42 Main St");
        address.setCreateBy("Create By");
        address.setCreateDate("2020-03-01");
        address.setId(123L);
        address.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        address.setModifiedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        address.setOrders(new HashSet<>());
        address.setPostalCode("Postal Code");
        address.setStatus(true);
        address.setUserAddresses(new HashSet<>());
        address.setVillage(village);
        Optional<Address> ofResult = Optional.of(address);
        when(iAddressRepository.findById((Long) any())).thenReturn(ofResult);

        City city1 = new City();
        city1.setDistricts(new HashSet<>());
        city1.setId(123L);
        city1.setName("Name");
        city1.setStatus(true);

        District district1 = new District();
        district1.setCity(city1);
        district1.setId(123L);
        district1.setName("Name");
        district1.setStatus(true);
        district1.setVillages(new HashSet<>());

        Village village1 = new Village();
        village1.setAddresses(new HashSet<>());
        village1.setDistrict(district1);
        village1.setId(123L);
        village1.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1("42 Main St");
        addressDTO.setAddressLine2("42 Main St");
        addressDTO.setCreateBy("Create By");
        addressDTO.setCreateDate("2020-03-01");
        addressDTO.setId(123L);
        addressDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO.setModifiedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO.setPostalCode("Postal Code");
        addressDTO.setStatus(true);
        addressDTO.setVillage(village1);
        addressServiceImpl.create(addressDTO);
    }

    /**
     * Method under test: {@link AddressServiceImpl#create(AddressDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreate2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "java.util.Optional.isPresent()" because the return value of "com.orange.services.impl.AddressServiceImpl.findByAddressDTO(com.orange.domain.dto.AddressDTO)" is null
        //       at com.orange.services.impl.AddressServiceImpl.create(AddressServiceImpl.java:35)
        //   See https://diff.blue/R013 to resolve this issue.

        when(iAddressRepository.findById((Long) any())).thenReturn(null);

        City city = new City();
        city.setDistricts(new HashSet<>());
        city.setId(123L);
        city.setName("Name");
        city.setStatus(true);

        District district = new District();
        district.setCity(city);
        district.setId(123L);
        district.setName("Name");
        district.setStatus(true);
        district.setVillages(new HashSet<>());

        Village village = new Village();
        village.setAddresses(new HashSet<>());
        village.setDistrict(district);
        village.setId(123L);
        village.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1("42 Main St");
        addressDTO.setAddressLine2("42 Main St");
        addressDTO.setCreateBy("Create By");
        addressDTO.setCreateDate("2020-03-01");
        addressDTO.setId(123L);
        addressDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO.setModifiedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO.setPostalCode("Postal Code");
        addressDTO.setStatus(true);
        addressDTO.setVillage(village);
        addressServiceImpl.create(addressDTO);
    }

    /**
     * Method under test: {@link AddressServiceImpl#create(AddressDTO)}
     */
    @Test
    void testCreate3() {
        City city = new City();
        city.setDistricts(new HashSet<>());
        city.setId(123L);
        city.setName("Name");
        city.setStatus(true);

        District district = new District();
        district.setCity(city);
        district.setId(123L);
        district.setName("Name");
        district.setStatus(true);
        district.setVillages(new HashSet<>());

        Village village = new Village();
        village.setAddresses(new HashSet<>());
        village.setDistrict(district);
        village.setId(123L);
        village.setName("Name");

        Address address = new Address();
        address.setAddressLine1("42 Main St");
        address.setAddressLine2("42 Main St");
        address.setCreateBy("Create By");
        address.setCreateDate("2020-03-01");
        address.setId(123L);
        address.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        address.setModifiedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        address.setOrders(new HashSet<>());
        address.setPostalCode("Postal Code");
        address.setStatus(true);
        address.setUserAddresses(new HashSet<>());
        address.setVillage(village);
        when(iAddressRepository.save((Address) any())).thenReturn(address);
        when(iAddressRepository.findById((Long) any())).thenReturn(Optional.empty());

        City city1 = new City();
        city1.setDistricts(new HashSet<>());
        city1.setId(123L);
        city1.setName("Name");
        city1.setStatus(true);

        District district1 = new District();
        district1.setCity(city1);
        district1.setId(123L);
        district1.setName("Name");
        district1.setStatus(true);
        district1.setVillages(new HashSet<>());

        Village village1 = new Village();
        village1.setAddresses(new HashSet<>());
        village1.setDistrict(district1);
        village1.setId(123L);
        village1.setName("Name");

        Address address1 = new Address();
        address1.setAddressLine1("42 Main St");
        address1.setAddressLine2("42 Main St");
        address1.setCreateBy("Create By");
        address1.setCreateDate("2020-03-01");
        address1.setId(123L);
        address1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        address1.setModifiedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        address1.setOrders(new HashSet<>());
        address1.setPostalCode("Postal Code");
        address1.setStatus(true);
        address1.setUserAddresses(new HashSet<>());
        address1.setVillage(village1);

        City city2 = new City();
        city2.setDistricts(new HashSet<>());
        city2.setId(123L);
        city2.setName("Name");
        city2.setStatus(true);

        District district2 = new District();
        district2.setCity(city2);
        district2.setId(123L);
        district2.setName("Name");
        district2.setStatus(true);
        district2.setVillages(new HashSet<>());

        Village village2 = new Village();
        village2.setAddresses(new HashSet<>());
        village2.setDistrict(district2);
        village2.setId(123L);
        village2.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1("42 Main St");
        addressDTO.setAddressLine2("42 Main St");
        addressDTO.setCreateBy("Create By");
        addressDTO.setCreateDate("2020-03-01");
        addressDTO.setId(123L);
        addressDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO.setModifiedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO.setPostalCode("Postal Code");
        addressDTO.setStatus(true);
        addressDTO.setVillage(village2);
        when(iAddressMapper.toDto((Address) any())).thenReturn(addressDTO);
        when(iAddressMapper.toEntity((AddressDTO) any())).thenReturn(address1);

        City city3 = new City();
        city3.setDistricts(new HashSet<>());
        city3.setId(123L);
        city3.setName("Name");
        city3.setStatus(true);

        District district3 = new District();
        district3.setCity(city3);
        district3.setId(123L);
        district3.setName("Name");
        district3.setStatus(true);
        district3.setVillages(new HashSet<>());

        Village village3 = new Village();
        village3.setAddresses(new HashSet<>());
        village3.setDistrict(district3);
        village3.setId(123L);
        village3.setName("Name");

        City city4 = new City();
        city4.setDistricts(new HashSet<>());
        city4.setId(123L);
        city4.setName("Name");
        city4.setStatus(true);

        District district4 = new District();
        district4.setCity(city4);
        district4.setId(123L);
        district4.setName("Name");
        district4.setStatus(true);
        district4.setVillages(new HashSet<>());

        Village village4 = new Village();
        village4.setAddresses(new HashSet<>());
        village4.setDistrict(district4);
        village4.setId(123L);
        village4.setName("Name");
        Optional<Village> ofResult = Optional.of(village4);
        when(iVillageRepository.save((Village) any())).thenReturn(village3);
        when(iVillageRepository.findById((Long) any())).thenReturn(ofResult);

        City city5 = new City();
        city5.setDistricts(new HashSet<>());
        city5.setId(123L);
        city5.setName("Name");
        city5.setStatus(true);

        District district5 = new District();
        district5.setCity(city5);
        district5.setId(123L);
        district5.setName("Name");
        district5.setStatus(true);
        district5.setVillages(new HashSet<>());

        City city6 = new City();
        city6.setDistricts(new HashSet<>());
        city6.setId(123L);
        city6.setName("Name");
        city6.setStatus(true);

        District district6 = new District();
        district6.setCity(city6);
        district6.setId(123L);
        district6.setName("Name");
        district6.setStatus(true);
        district6.setVillages(new HashSet<>());
        Optional<District> ofResult1 = Optional.of(district6);
        when(iDistrictRepository.save((District) any())).thenReturn(district5);
        when(iDistrictRepository.findById((Long) any())).thenReturn(ofResult1);

        City city7 = new City();
        city7.setDistricts(new HashSet<>());
        city7.setId(123L);
        city7.setName("Name");
        city7.setStatus(true);

        City city8 = new City();
        city8.setDistricts(new HashSet<>());
        city8.setId(123L);
        city8.setName("Name");
        city8.setStatus(true);
        Optional<City> ofResult2 = Optional.of(city8);
        when(iCityRepository.save((City) any())).thenReturn(city7);
        when(iCityRepository.findById((Long) any())).thenReturn(ofResult2);

        City city9 = new City();
        city9.setDistricts(new HashSet<>());
        city9.setId(123L);
        city9.setName("Name");
        city9.setStatus(true);

        District district7 = new District();
        district7.setCity(city9);
        district7.setId(123L);
        district7.setName("Name");
        district7.setStatus(true);
        district7.setVillages(new HashSet<>());

        Village village5 = new Village();
        village5.setAddresses(new HashSet<>());
        village5.setDistrict(district7);
        village5.setId(123L);
        village5.setName("Name");

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setAddressLine1("42 Main St");
        addressDTO1.setAddressLine2("42 Main St");
        addressDTO1.setCreateBy("Create By");
        addressDTO1.setCreateDate("2020-03-01");
        addressDTO1.setId(123L);
        addressDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO1.setModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO1.setPostalCode("Postal Code");
        addressDTO1.setStatus(true);
        addressDTO1.setVillage(village5);
        assertSame(addressDTO, addressServiceImpl.create(addressDTO1));
        verify(iAddressRepository).save((Address) any());
        verify(iAddressRepository).findById((Long) any());
        verify(iAddressMapper).toDto((Address) any());
        verify(iAddressMapper).toEntity((AddressDTO) any());
        verify(iVillageRepository).findById((Long) any());
        verify(iDistrictRepository).findById((Long) any());
        verify(iCityRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#update(AddressDTO)}
     */
    @Test
    void testUpdate() {
        City city = new City();
        city.setDistricts(new HashSet<>());
        city.setId(123L);
        city.setName("Name");
        city.setStatus(true);

        District district = new District();
        district.setCity(city);
        district.setId(123L);
        district.setName("Name");
        district.setStatus(true);
        district.setVillages(new HashSet<>());

        Village village = new Village();
        village.setAddresses(new HashSet<>());
        village.setDistrict(district);
        village.setId(123L);
        village.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1("42 Main St");
        addressDTO.setAddressLine2("42 Main St");
        addressDTO.setCreateBy("Create By");
        addressDTO.setCreateDate("2020-03-01");
        addressDTO.setId(123L);
        addressDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO.setModifiedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO.setPostalCode("Postal Code");
        addressDTO.setStatus(true);
        addressDTO.setVillage(village);
        assertNull(addressServiceImpl.update(addressDTO));
    }

    /**
     * Method under test: {@link AddressServiceImpl#update(AddressDTO)}
     */
    @Test
    void testUpdate2() {
        City city = new City();
        city.setDistricts(new HashSet<>());
        city.setId(123L);
        city.setName("Name");
        city.setStatus(true);

        District district = new District();
        district.setCity(city);
        district.setId(123L);
        district.setName("Name");
        district.setStatus(true);
        district.setVillages(new HashSet<>());

        Village village = new Village();
        village.setAddresses(new HashSet<>());
        village.setDistrict(district);
        village.setId(123L);
        village.setName("Name");
        AddressDTO addressDTO = mock(AddressDTO.class);
        doNothing().when(addressDTO).setAddressLine1((String) any());
        doNothing().when(addressDTO).setAddressLine2((String) any());
        doNothing().when(addressDTO).setPostalCode((String) any());
        doNothing().when(addressDTO).setStatus((Boolean) any());
        doNothing().when(addressDTO).setVillage((Village) any());
        doNothing().when(addressDTO).setCreateBy((String) any());
        doNothing().when(addressDTO).setCreateDate((String) any());
        doNothing().when(addressDTO).setId((Long) any());
        doNothing().when(addressDTO).setModifiedBy((String) any());
        doNothing().when(addressDTO).setModifiedDate((Date) any());
        addressDTO.setAddressLine1("42 Main St");
        addressDTO.setAddressLine2("42 Main St");
        addressDTO.setCreateBy("Create By");
        addressDTO.setCreateDate("2020-03-01");
        addressDTO.setId(123L);
        addressDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO.setModifiedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO.setPostalCode("Postal Code");
        addressDTO.setStatus(true);
        addressDTO.setVillage(village);
        assertNull(addressServiceImpl.update(addressDTO));
        verify(addressDTO).setAddressLine1((String) any());
        verify(addressDTO).setAddressLine2((String) any());
        verify(addressDTO).setPostalCode((String) any());
        verify(addressDTO).setStatus((Boolean) any());
        verify(addressDTO).setVillage((Village) any());
        verify(addressDTO).setCreateBy((String) any());
        verify(addressDTO).setCreateDate((String) any());
        verify(addressDTO).setId((Long) any());
        verify(addressDTO).setModifiedBy((String) any());
        verify(addressDTO).setModifiedDate((Date) any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#delete(AddressDTO)}
     */
    @Test
    void testDelete() {
        City city = new City();
        city.setDistricts(new HashSet<>());
        city.setId(123L);
        city.setName("Name");
        city.setStatus(true);

        District district = new District();
        district.setCity(city);
        district.setId(123L);
        district.setName("Name");
        district.setStatus(true);
        district.setVillages(new HashSet<>());

        Village village = new Village();
        village.setAddresses(new HashSet<>());
        village.setDistrict(district);
        village.setId(123L);
        village.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1("42 Main St");
        addressDTO.setAddressLine2("42 Main St");
        addressDTO.setCreateBy("Create By");
        addressDTO.setCreateDate("2020-03-01");
        addressDTO.setId(123L);
        addressDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO.setModifiedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO.setPostalCode("Postal Code");
        addressDTO.setStatus(true);
        addressDTO.setVillage(village);
        assertNull(addressServiceImpl.delete(addressDTO));
    }

    /**
     * Method under test: {@link AddressServiceImpl#delete(AddressDTO)}
     */
    @Test
    void testDelete2() {
        City city = new City();
        city.setDistricts(new HashSet<>());
        city.setId(123L);
        city.setName("Name");
        city.setStatus(true);

        District district = new District();
        district.setCity(city);
        district.setId(123L);
        district.setName("Name");
        district.setStatus(true);
        district.setVillages(new HashSet<>());

        Village village = new Village();
        village.setAddresses(new HashSet<>());
        village.setDistrict(district);
        village.setId(123L);
        village.setName("Name");
        AddressDTO addressDTO = mock(AddressDTO.class);
        doNothing().when(addressDTO).setAddressLine1((String) any());
        doNothing().when(addressDTO).setAddressLine2((String) any());
        doNothing().when(addressDTO).setPostalCode((String) any());
        doNothing().when(addressDTO).setStatus((Boolean) any());
        doNothing().when(addressDTO).setVillage((Village) any());
        doNothing().when(addressDTO).setCreateBy((String) any());
        doNothing().when(addressDTO).setCreateDate((String) any());
        doNothing().when(addressDTO).setId((Long) any());
        doNothing().when(addressDTO).setModifiedBy((String) any());
        doNothing().when(addressDTO).setModifiedDate((Date) any());
        addressDTO.setAddressLine1("42 Main St");
        addressDTO.setAddressLine2("42 Main St");
        addressDTO.setCreateBy("Create By");
        addressDTO.setCreateDate("2020-03-01");
        addressDTO.setId(123L);
        addressDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO.setModifiedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO.setPostalCode("Postal Code");
        addressDTO.setStatus(true);
        addressDTO.setVillage(village);
        assertNull(addressServiceImpl.delete(addressDTO));
        verify(addressDTO).setAddressLine1((String) any());
        verify(addressDTO).setAddressLine2((String) any());
        verify(addressDTO).setPostalCode((String) any());
        verify(addressDTO).setStatus((Boolean) any());
        verify(addressDTO).setVillage((Village) any());
        verify(addressDTO).setCreateBy((String) any());
        verify(addressDTO).setCreateDate((String) any());
        verify(addressDTO).setId((Long) any());
        verify(addressDTO).setModifiedBy((String) any());
        verify(addressDTO).setModifiedDate((Date) any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#fillAll(Pageable)}
     */
    @Test
    void testFillAll() {
        ArrayList<Address> addressList = new ArrayList<>();
        when(iAddressRepository.findAll((Pageable) any())).thenReturn(new PageImpl<>(addressList));
        com.orange.common.payload.Page<?> actualFillAllResult = addressServiceImpl.fillAll(null);
        assertEquals(0, actualFillAllResult.getPageNumber());
        assertEquals(1, actualFillAllResult.getTotalPages());
        assertEquals(0, actualFillAllResult.getPageSize());
        assertEquals(0, actualFillAllResult.getTotalItems());
        assertEquals(addressList, actualFillAllResult.getResult());
        verify(iAddressRepository).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#fillAll(Pageable)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFillAll2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.data.domain.Page.getContent()" because "result" is null
        //       at com.orange.services.impl.AddressServiceImpl.fillAll(AddressServiceImpl.java:58)
        //   See https://diff.blue/R013 to resolve this issue.

        when(iAddressRepository.findAll((Pageable) any())).thenReturn(null);
        addressServiceImpl.fillAll(null);
    }

    /**
     * Method under test: {@link AddressServiceImpl#fillAddressByUser(Long)}
     */
    @Test
    void testFillAddressByUser() {
        when(iAddressRepository.findAddressByUser((Long) any())).thenReturn(new ArrayList<>());
        ArrayList<AddressDTO> addressDTOList = new ArrayList<>();
        when(iAddressMapper.toDtoList((Collection<Address>) any())).thenReturn(addressDTOList);
        List<AddressDTO> actualFillAddressByUserResult = addressServiceImpl.fillAddressByUser(123L);
        assertSame(addressDTOList, actualFillAddressByUserResult);
        assertTrue(actualFillAddressByUserResult.isEmpty());
        verify(iAddressRepository).findAddressByUser((Long) any());
        verify(iAddressMapper).toDtoList((Collection<Address>) any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#findById(Long)}
     */
    @Test
    void testFindById() {
        City city = new City();
        city.setDistricts(new HashSet<>());
        city.setId(123L);
        city.setName("Name");
        city.setStatus(true);

        District district = new District();
        district.setCity(city);
        district.setId(123L);
        district.setName("Name");
        district.setStatus(true);
        district.setVillages(new HashSet<>());

        Village village = new Village();
        village.setAddresses(new HashSet<>());
        village.setDistrict(district);
        village.setId(123L);
        village.setName("Name");

        Address address = new Address();
        address.setAddressLine1("42 Main St");
        address.setAddressLine2("42 Main St");
        address.setCreateBy("Create By");
        address.setCreateDate("2020-03-01");
        address.setId(123L);
        address.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        address.setModifiedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        address.setOrders(new HashSet<>());
        address.setPostalCode("Postal Code");
        address.setStatus(true);
        address.setUserAddresses(new HashSet<>());
        address.setVillage(village);
        Optional<Address> ofResult = Optional.of(address);
        when(iAddressRepository.findById((Long) any())).thenReturn(ofResult);

        City city1 = new City();
        city1.setDistricts(new HashSet<>());
        city1.setId(123L);
        city1.setName("Name");
        city1.setStatus(true);

        District district1 = new District();
        district1.setCity(city1);
        district1.setId(123L);
        district1.setName("Name");
        district1.setStatus(true);
        district1.setVillages(new HashSet<>());

        Village village1 = new Village();
        village1.setAddresses(new HashSet<>());
        village1.setDistrict(district1);
        village1.setId(123L);
        village1.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1("42 Main St");
        addressDTO.setAddressLine2("42 Main St");
        addressDTO.setCreateBy("Create By");
        addressDTO.setCreateDate("2020-03-01");
        addressDTO.setId(123L);
        addressDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO.setModifiedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO.setPostalCode("Postal Code");
        addressDTO.setStatus(true);
        addressDTO.setVillage(village1);
        when(iAddressMapper.toDto((Address) any())).thenReturn(addressDTO);
        assertSame(addressDTO, addressServiceImpl.findById(123L));
        verify(iAddressRepository).findById((Long) any());
        verify(iAddressMapper).toDto((Address) any());
    }

    /**
     * Method under test: {@link AddressServiceImpl#findById(Long)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testFindById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.orange.config.MessageProperties.getConfigValue(String)" because "com.orange.exception.GlobalException.propertiesConfig" is null
        //       at com.orange.exception.GlobalException.format(GlobalException.java:94)
        //       at com.orange.exception.GlobalException.throwException(GlobalException.java:80)
        //       at com.orange.exception.GlobalException.throwException(GlobalException.java:41)
        //       at com.orange.services.impl.AddressServiceImpl.lambda$getAddressById$0(AddressServiceImpl.java:101)
        //       at java.util.Optional.orElseThrow(Optional.java:403)
        //       at com.orange.services.impl.AddressServiceImpl.getAddressById(AddressServiceImpl.java:101)
        //       at com.orange.services.impl.AddressServiceImpl.findById(AddressServiceImpl.java:75)
        //   See https://diff.blue/R013 to resolve this issue.

        when(iAddressRepository.findById((Long) any())).thenReturn(Optional.empty());

        City city = new City();
        city.setDistricts(new HashSet<>());
        city.setId(123L);
        city.setName("Name");
        city.setStatus(true);

        District district = new District();
        district.setCity(city);
        district.setId(123L);
        district.setName("Name");
        district.setStatus(true);
        district.setVillages(new HashSet<>());

        Village village = new Village();
        village.setAddresses(new HashSet<>());
        village.setDistrict(district);
        village.setId(123L);
        village.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1("42 Main St");
        addressDTO.setAddressLine2("42 Main St");
        addressDTO.setCreateBy("Create By");
        addressDTO.setCreateDate("2020-03-01");
        addressDTO.setId(123L);
        addressDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO.setModifiedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO.setPostalCode("Postal Code");
        addressDTO.setStatus(true);
        addressDTO.setVillage(village);
        when(iAddressMapper.toDto((Address) any())).thenReturn(addressDTO);
        addressServiceImpl.findById(123L);
    }

    /**
     * Method under test: {@link AddressServiceImpl#addUserAddress(User, AddressDTO)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testAddUserAddress() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.orange.config.MessageProperties.getConfigValue(String)" because "com.orange.exception.GlobalException.propertiesConfig" is null
        //       at com.orange.exception.GlobalException.format(GlobalException.java:94)
        //       at com.orange.exception.GlobalException.throwException(GlobalException.java:84)
        //       at com.orange.exception.GlobalException.throwException(GlobalException.java:41)
        //       at com.orange.services.impl.AddressServiceImpl.addUserAddress(AddressServiceImpl.java:93)
        //   See https://diff.blue/R013 to resolve this issue.

        City city = new City();
        city.setDistricts(new HashSet<>());
        city.setId(123L);
        city.setName("Name");
        city.setStatus(true);

        District district = new District();
        district.setCity(city);
        district.setId(123L);
        district.setName("Name");
        district.setStatus(true);
        district.setVillages(new HashSet<>());

        Village village = new Village();
        village.setAddresses(new HashSet<>());
        village.setDistrict(district);
        village.setId(123L);
        village.setName("Name");

        Address address = new Address();
        address.setAddressLine1("42 Main St");
        address.setAddressLine2("42 Main St");
        address.setCreateBy("Create By");
        address.setCreateDate("2020-03-01");
        address.setId(123L);
        address.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        address.setModifiedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        address.setOrders(new HashSet<>());
        address.setPostalCode("Postal Code");
        address.setStatus(true);
        address.setUserAddresses(new HashSet<>());
        address.setVillage(village);
        Optional<Address> ofResult = Optional.of(address);
        when(iAddressRepository.findById((Long) any())).thenReturn(ofResult);

        City city1 = new City();
        city1.setDistricts(new HashSet<>());
        city1.setId(123L);
        city1.setName("Name");
        city1.setStatus(true);

        District district1 = new District();
        district1.setCity(city1);
        district1.setId(123L);
        district1.setName("Name");
        district1.setStatus(true);
        district1.setVillages(new HashSet<>());

        Village village1 = new Village();
        village1.setAddresses(new HashSet<>());
        village1.setDistrict(district1);
        village1.setId(123L);
        village1.setName("Name");

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setAddressLine1("42 Main St");
        addressDTO.setAddressLine2("42 Main St");
        addressDTO.setCreateBy("Create By");
        addressDTO.setCreateDate("2020-03-01");
        addressDTO.setId(123L);
        addressDTO.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO.setModifiedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO.setPostalCode("Postal Code");
        addressDTO.setStatus(true);
        addressDTO.setVillage(village1);
        when(iAddressMapper.toDto((Address) any())).thenReturn(addressDTO);

        City city2 = new City();
        city2.setDistricts(new HashSet<>());
        city2.setId(123L);
        city2.setName("Name");
        city2.setStatus(true);

        District district2 = new District();
        district2.setCity(city2);
        district2.setId(123L);
        district2.setName("Name");
        district2.setStatus(true);
        district2.setVillages(new HashSet<>());

        Village village2 = new Village();
        village2.setAddresses(new HashSet<>());
        village2.setDistrict(district2);
        village2.setId(123L);
        village2.setName("Name");

        Address address1 = new Address();
        address1.setAddressLine1("42 Main St");
        address1.setAddressLine2("42 Main St");
        address1.setCreateBy("Create By");
        address1.setCreateDate("2020-03-01");
        address1.setId(123L);
        address1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        address1.setModifiedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        address1.setOrders(new HashSet<>());
        address1.setPostalCode("Postal Code");
        address1.setStatus(true);
        address1.setUserAddresses(new HashSet<>());
        address1.setVillage(village2);

        User user = new User();
        user.setActivate(true);
        user.setAvatar("Avatar");
        user.setCreateBy("Create By");
        user.setCreateDate("2020-03-01");
        user.setEmail("jane.doe@example.org");
        user.setFirstName("Jane");
        user.setId(123L);
        user.setLastName("Doe");
        user.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setModifiedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user.setPasswordHash("Password Hash");
        user.setPhone("4105551212");
        user.setRoles(new HashSet<>());
        user.setUserAddresses(new HashSet<>());
        user.setUserPaymentMethods(new HashSet<>());
        user.setUserReviews(new HashSet<>());
        user.setUsername("janedoe");

        UserAddress userAddress = new UserAddress();
        userAddress.setAddress(address1);
        userAddress.setCreateBy("Create By");
        userAddress.setCreateDate("2020-03-01");
        userAddress.setId(123L);
        userAddress.setIsDefault((byte) 'A');
        userAddress.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userAddress.setModifiedDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        userAddress.setUser(user);

        District district3 = new District();
        district3.setCity(new City());
        district3.setId(123L);
        district3.setName("Name");
        district3.setStatus(true);
        district3.setVillages(new HashSet<>());

        Village village3 = new Village();
        village3.setAddresses(new HashSet<>());
        village3.setDistrict(district3);
        village3.setId(123L);
        village3.setName("Name");

        Address address2 = new Address();
        address2.setAddressLine1("42 Main St");
        address2.setAddressLine2("42 Main St");
        address2.setCreateBy("Create By");
        address2.setCreateDate("2020-03-01");
        address2.setId(123L);
        address2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        address2.setModifiedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        address2.setOrders(new HashSet<>());
        address2.setPostalCode("Postal Code");
        address2.setStatus(true);
        address2.setUserAddresses(new HashSet<>());
        address2.setVillage(village3);

        User user1 = new User();
        user1.setActivate(true);
        user1.setAvatar("Avatar");
        user1.setCreateBy("Create By");
        user1.setCreateDate("2020-03-01");
        user1.setEmail("jane.doe@example.org");
        user1.setFirstName("Jane");
        user1.setId(123L);
        user1.setLastName("Doe");
        user1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setModifiedDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setPasswordHash("Password Hash");
        user1.setPhone("4105551212");
        user1.setRoles(new HashSet<>());
        user1.setUserAddresses(new HashSet<>());
        user1.setUserPaymentMethods(new HashSet<>());
        user1.setUserReviews(new HashSet<>());
        user1.setUsername("janedoe");

        UserAddress userAddress1 = new UserAddress();
        userAddress1.setAddress(address2);
        userAddress1.setCreateBy("Create By");
        userAddress1.setCreateDate("2020-03-01");
        userAddress1.setId(123L);
        userAddress1.setIsDefault((byte) 'A');
        userAddress1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        userAddress1.setModifiedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        userAddress1.setUser(user1);
        Optional<UserAddress> ofResult1 = Optional.of(userAddress1);
        when(iUserAddressRepository.save((UserAddress) any())).thenReturn(userAddress);
        when(iUserAddressRepository.findFirstByUser_IdAndAddress_Id((Long) any(), (Long) any())).thenReturn(ofResult1);

        User user2 = new User();
        user2.setActivate(true);
        user2.setAvatar("Avatar");
        user2.setCreateBy("Create By");
        user2.setCreateDate("2020-03-01");
        user2.setEmail("jane.doe@example.org");
        user2.setFirstName("Jane");
        user2.setId(123L);
        user2.setLastName("Doe");
        user2.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setModifiedDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setPasswordHash("Password Hash");
        user2.setPhone("4105551212");
        user2.setRoles(new HashSet<>());
        user2.setUserAddresses(new HashSet<>());
        user2.setUserPaymentMethods(new HashSet<>());
        user2.setUserReviews(new HashSet<>());
        user2.setUsername("janedoe");

        City city3 = new City();
        city3.setDistricts(new HashSet<>());
        city3.setId(123L);
        city3.setName("Name");
        city3.setStatus(true);

        District district4 = new District();
        district4.setCity(city3);
        district4.setId(123L);
        district4.setName("Name");
        district4.setStatus(true);
        district4.setVillages(new HashSet<>());

        Village village4 = new Village();
        village4.setAddresses(new HashSet<>());
        village4.setDistrict(district4);
        village4.setId(123L);
        village4.setName("Name");

        AddressDTO addressDTO1 = new AddressDTO();
        addressDTO1.setAddressLine1("42 Main St");
        addressDTO1.setAddressLine2("42 Main St");
        addressDTO1.setCreateBy("Create By");
        addressDTO1.setCreateDate("2020-03-01");
        addressDTO1.setId(123L);
        addressDTO1.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        addressDTO1.setModifiedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        addressDTO1.setPostalCode("Postal Code");
        addressDTO1.setStatus(true);
        addressDTO1.setVillage(village4);
        addressServiceImpl.addUserAddress(user2, addressDTO1);
    }
}

