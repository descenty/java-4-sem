package com.mirea.practice18;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rsa")
public record RsaProperties(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
}