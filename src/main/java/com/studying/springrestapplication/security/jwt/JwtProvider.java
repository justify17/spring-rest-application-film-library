package com.studying.springrestapplication.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Slf4j
@Component
public class JwtProvider {
    public static final long ACCESS_JWT_MILLIS_LIFETIME = 1_800_000;
    public static final long REFRESH_JWT_MILLIS_LIFETIME = 86_400_000;

    private final Key accessSecretKey;
    private final Key refreshSecretKey;

    public JwtProvider(@Value("${jwt.secret.access}") String accessSecret,
                       @Value("${jwt.secret.refresh}") String refreshSecret) {
        byte[] byteAccessKey = Decoders.BASE64.decode(accessSecret);
        accessSecretKey = Keys.hmacShaKeyFor(byteAccessKey);

        byte[] byteRefreshKey = Decoders.BASE64.decode(refreshSecret);
        refreshSecretKey = Keys.hmacShaKeyFor(byteRefreshKey);
    }

    public String generateAccessToken(String username) {
        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + ACCESS_JWT_MILLIS_LIFETIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issuedDate)
                .setExpiration(expirationDate)
                .signWith(accessSecretKey)
                .compact();
    }

    public String generateRefreshToken(String username) {
        Date issuedDate = new Date();
        Date expirationDate = new Date(issuedDate.getTime() + REFRESH_JWT_MILLIS_LIFETIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issuedDate)
                .setExpiration(expirationDate)
                .signWith(refreshSecretKey)
                .compact();
    }

    public boolean isAccessTokenValid(String token) {

        return isTokenValid(accessSecretKey, token);
    }

    public boolean isRefreshTokenValid(String token) {

        return isTokenValid(refreshSecretKey, token);
    }

    private boolean isTokenValid(Key secretKey, String token) {
        try {
            getJwt(secretKey, token);

            return true;
        } catch (JwtException e) {
            log.error("Authentication error. Token is not valid");

            return false;
        }
    }

    private Jws<Claims> getJwt(Key secretKey, String token) {

        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token);
    }

    public Claims getAccessTokenClaims(String token) {

        return getTokenClaims(accessSecretKey, token);
    }

    public Claims getRefreshTokenClaims(String token) {

        return getTokenClaims(refreshSecretKey, token);
    }

    private Claims getTokenClaims(Key secretKey, String token) {
        Jws<Claims> jwt = getJwt(secretKey, token);

        return jwt.getBody();
    }
}
