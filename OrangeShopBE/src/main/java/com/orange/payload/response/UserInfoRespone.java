package com.orange.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
public class UserInfoRespone {
    private String username;
    private String firstName;
    private String lastName;
    private String avatar;
    private String phone;
    private String email;
    private Set<String> roles = new HashSet<>();
}
