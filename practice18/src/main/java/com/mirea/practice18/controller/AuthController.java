package com.mirea.practice18.controller;

import java.text.ParseException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mirea.practice18.service.CustomUserDetails;
import com.mirea.practice18.service.CustomUserDetailsService;
import com.mirea.practice18.service.TokenService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthController {

    private final TokenService tokenService;
    private final AuthenticationManager authManager;
    private final CustomUserDetailsService usrDetailsService;

    record LoginRequest(String username, String password) {
    };

    record LoginResponse(String message, String access_jwt_token, String refresh_jwt_token) {
    };

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.username, request.password);
        authManager.authenticate(authenticationToken);

        CustomUserDetails user = (CustomUserDetails) usrDetailsService.loadUserByUsername(request.username);
        String access_token = tokenService.generateAccessToken(user);
        String refresh_token = tokenService.generateRefreshToken(user);

        return new LoginResponse("User with email = " + request.username + " successfully logined!"

                , access_token, refresh_token);
    }

    record RefreshTokenResponse(String access_jwt_token, String refresh_jwt_token) {
    };

    @GetMapping("/token/refresh")
    public RefreshTokenResponse refreshToken(HttpServletRequest request) throws ParseException {
        String headerAuth = request.getHeader("Authorization");
        String refreshToken = headerAuth.substring(7, headerAuth.length());

        String email = tokenService.parseToken(refreshToken);
        CustomUserDetails user = (CustomUserDetails) usrDetailsService.loadUserByUsername(email);
        String access_token = tokenService.generateAccessToken(user);
        String refresh_token = tokenService.generateRefreshToken(user);

        return new RefreshTokenResponse(access_token, refresh_token);
    }
}