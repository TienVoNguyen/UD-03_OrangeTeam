package com.orange.payload.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtRequest {

    private String username;
    private String password;
}
