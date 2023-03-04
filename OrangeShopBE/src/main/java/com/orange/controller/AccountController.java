package com.orange.controller;


import com.orange.common.payload.Result;
import com.orange.payload.request.JwtRequest;
import com.orange.payload.response.UserInfoRespone;
import com.orange.security.services.UserDetailsServiceImpl;
import com.orange.services.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping
@RequiredArgsConstructor
public class AccountController {

    private final UserDetailsServiceImpl userDetailService;
    private final AuthenticationManager authenticationManager;

    private final IUserService userService;

    @PostMapping("/authenticate")
    public Result<?> authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Map<String, String> jsonToken = new HashMap<>();
        jsonToken.put("token", userDetailService.createJwtToken(jwtRequest));
        return Result.result(HttpStatus.OK.value(), "Đăng nhập thành công", jsonToken);
    }

    @GetMapping("/sign-up")
    public Result<?>  signUp() throws Exception {

        return authenticate(new JwtRequest("a","a"));
    }
    @GetMapping("/user/info")
    public Result<?>  getUserInfo() throws Exception {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        UserInfoRespone info = this.userService.getUserInfo(username);
        return Result.result(HttpStatus.OK.value(), "Lấy thông tin người dùng thành công", info);
    }
}
