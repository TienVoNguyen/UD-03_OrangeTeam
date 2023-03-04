package com.orange.services;

import com.orange.domain.dto.UserDTO;
import com.orange.domain.model.User;
import com.orange.payload.response.UserInfoRespone;

public interface IUserService extends BaseService<UserDTO, Long>{

    UserInfoRespone getUserInfo(String username);

    User findByUsername(String username);
}
