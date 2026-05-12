package com.itluffy.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // JWT签名密钥，至少32个字符，建议放到配置文件中并通过配置注入
    private static final String SECRET_KEY = "tlias-web-management-jwt-secret-key-0123456789";

    // 过期时间，单位：毫秒，默认24小时
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000L;

    private JwtUtils() {
    }

    public static String generateJwt(Map<String, ?> claims) {
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSecretKey())
                .compact();
    }

    public static Claims parseJwt(String jwt) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public static String getSubject(String jwt) {
        return parseJwt(jwt).getSubject();
    }

    public static Long getId(String jwt) {
        Object id = parseJwt(jwt).get("id");
        if (id == null) {
            return null;
        }
        if (id instanceof Number number) {
            return number.longValue();
        }
        return Long.valueOf(id.toString());
    }

    public static boolean isExpired(String jwt) {
        return parseJwt(jwt).getExpiration().before(new Date());
    }

    private static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
}
