package com.mirea.practice18.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.nimbusds.jwt.SignedJWT;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenService  {
	
	private final JwtEncoder jwtEncoder;
	
    public String generateAccessToken(CustomUserDetails usrDetails) {
        Instant now = Instant.now();
        String scope = usrDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(2, ChronoUnit.MINUTES))
                .subject(usrDetails.getUsername())
                .claim("scope", scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String generateRefreshToken(CustomUserDetails usrDetails) {
        Instant now = Instant.now();
        String scope = usrDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(10, ChronoUnit.MINUTES))
                .subject(usrDetails.getUsername())
                .claim("scope", scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    
    public String parseToken(String token) throws java.text.ParseException {
    	SignedJWT decodedJWT = SignedJWT.parse(token);
        String subject = decodedJWT.getJWTClaimsSet().getSubject();
        return subject;
    }
}