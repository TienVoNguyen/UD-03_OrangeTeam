package com.orange.security.services;

import com.orange.domain.model.User;
import com.orange.payload.request.JwtRequest;
import com.orange.payload.response.JwtRespone;
import com.orange.repositories.IUserRepository;
import com.orange.security.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserRepository userDAO;

    private final JwtUtils jwtUtil;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public JwtRespone createJwtToken(JwtRequest jwtRequest) throws Exception {
        String username = jwtRequest.getUsername();
        final UserDetails userDetails = loadUserByUsername(username);
        User user = userDAO.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        String newGeneratedToken = jwtUtil.generateJwtToken((UserDetailsImpl) userDetails);
        Set<String> roles = user.getRoles().stream().map(role -> role.getName().getValue()).collect(Collectors.toSet());
        return JwtRespone.builder()
                .jwtToken(newGeneratedToken)
                .roles(roles)
                .username(userDetails.getUsername())
                .email(user.getEmail())
                .avatar(user.getAvatar())
                .phone(user.getPhone())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }


}
