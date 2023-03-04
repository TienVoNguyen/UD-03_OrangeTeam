package com.orange.services.impl;

import com.orange.common.payload.Page;
import com.orange.domain.dto.UserDTO;
import com.orange.domain.model.User;
import com.orange.payload.response.UserInfoRespone;
import com.orange.repositories.IUserRepository;
import com.orange.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    @Override
    public UserDTO create(UserDTO dto) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO dto) {
        return null;
    }

    @Override
    public UserDTO delete(UserDTO dto) {
        return null;
    }

    @Override
    public Page<?> fillAll(Pageable pageable) {
        return null;
    }

    @Override
    public UserDTO findById(Long aLong) {
        return null;
    }

    @Override
    public UserInfoRespone getUserInfo(String username) {
        User user = findByUsername(username);
        return UserInfoRespone.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .roles(user.getRoles().stream().map(r -> r.getName().getValue()).collect(Collectors.toSet()))
                .build();
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }
}
