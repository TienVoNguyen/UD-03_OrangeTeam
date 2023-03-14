package com.orange.controller;


import com.orange.Utils.AccountUtils;
import com.orange.common.payload.Page;
import com.orange.common.payload.Result;
import com.orange.domain.dto.AddressDTO;
import com.orange.domain.model.User;
import com.orange.exception.EntityType;
import com.orange.exception.ExceptionType;
import com.orange.exception.GlobalException;
import com.orange.repositories.IUserRepository;
import com.orange.services.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final IAddressService addressService;
    private final IUserRepository userRepository;

    @GetMapping("")
    public Result<?> getAllAddress(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "20") int size) {
        Page<?> pages = this.addressService.fillAll(PageRequest.of(page, size));
        return Result.result(HttpStatus.OK.value(), "Lấy dữ liệu adddress thành công!", pages);
    }

    @GetMapping("/by-user")
    public Result<?> getAddressByUser() {
        Optional<User> user = userRepository.findByUsername(AccountUtils.getUsername());
        if (user.isEmpty()) {
            throw GlobalException.throwException(EntityType.sysUser, ExceptionType.ENTITY_NOT_FOUND, "unauthenticated");
        }
        List<AddressDTO> list = this.addressService.fillAddressByUser(user.get().getId());
        return Result.result(HttpStatus.OK.value(), "Lấy dữ liệu adddress thành công!", list);
    }

    @PostMapping("/add-user-address")
    public Result<?> addAddressForUser(@RequestBody Optional<AddressDTO> addressDTO) {
        if (addressDTO.isPresent()) {
            Optional<User> user = userRepository.findByUsername(AccountUtils.getUsername());
            if (user.isPresent()) {
                AddressDTO result = addressService.addUserAddress(user.get(), addressDTO.get());
                return Result.result(HttpStatus.OK.value(), "Thêm adddress thành công!", result);
            } else {
                throw GlobalException.throwException(EntityType.sysUser, ExceptionType.UNAUTHENTICATED, "unauthenticated");
            }
        } else {
            throw GlobalException.throwException(EntityType.product, ExceptionType.ENTITY_NOT_FOUND, "không có address!");
        }
    }
    @GetMapping("/set-default")
    public Result<?> setDefaultAddressForUser(@RequestParam("addressId") Optional<Long> addressId){
        if (addressId.isPresent()) {
            User user = getUser();
            AddressDTO result = addressService.setDefaultAddressForUser(user, addressId.get());
            return Result.result(HttpStatus.OK.value(), "Cập nhật địa chỉ mặc định thành công!", result);
        } else {
            throw GlobalException.throwException(EntityType.product, ExceptionType.ENTITY_NOT_FOUND, "không có address!");
        }
    }

    private User getUser() {
        Optional<User> user = userRepository.findByUsername(AccountUtils.getUsername());
        if (user.isPresent()) {
            return user.get();
        } else {
            throw GlobalException.throwException(EntityType.sysUser, ExceptionType.UNAUTHENTICATED, "unauthenticated");
        }
    }

}
