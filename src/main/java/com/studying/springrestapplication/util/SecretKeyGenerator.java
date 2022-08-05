package com.studying.springrestapplication.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class SecretKeyGenerator {
    public static void main(String[] args) {
        System.out.println("New secret key:\n" + generateSecretKey());
        System.out.println("New secret key:\n" + generateSecretKey());
    }

    private static String generateSecretKey() {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        return Encoders.BASE64.encode(secretKey.getEncoded());
    }
}
