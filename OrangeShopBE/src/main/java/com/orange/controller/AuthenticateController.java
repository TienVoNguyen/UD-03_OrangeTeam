package com.orange.controller;


import com.orange.common.payload.Result;
import com.orange.payload.request.JwtRequest;
import com.orange.payload.response.JwtRespone;
import com.orange.security.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping
@RequiredArgsConstructor
public class AuthenticateController {

    private final UserDetailsServiceImpl userDetailService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/authenticate")
    public Result<?> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return Result.result(HttpStatus.OK.value(), "Đăng nhập thành công", userDetailService.createJwtToken(jwtRequest));
    }
}
