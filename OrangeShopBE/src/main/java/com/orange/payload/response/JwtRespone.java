package com.orange.payload.response;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtRespone {

    private String username;
    private String firstName;
    private String lastName;
    private String avatar;
    private String phone;
    private String email;
    private Set<String> roles = new HashSet<>();
    private String jwtToken;
}
