package com.itluffy;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JWTTest {

    // 密钥字符串，实际项目中建议放到配置文件中，不要硬编码在代码里
    private static final String SECRET = "aXRsdWZmeQ==aXRsdWZmeQ==aXRsdWZmeQ==";

    // 生成签名用的密钥对象，签发和解析 JWT 时都要使用同一个密钥
    private static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }

    /*
     * 生成JWT令牌
     * */
    @Test
    public void testBuildJWT() {
        Map<String, Object> dataMap = Map.of("id", 1, "username", "admin");

        // builder()：创建 JWT 构建器
        // claims(...)：放入自定义载荷数据
        // expiration(...)：设置过期时间
        // signWith(...)：使用密钥签名
        // compact()：生成最终的 token 字符串
        String jwt = Jwts.builder()
                .claims(dataMap)
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24))
                .signWith(getSigningKey())
                .compact();
        System.out.println(jwt);
    }

    /*
     * 解析JWT令牌
     * */
    @Test
    public void testParseJWT() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwiaWQiOjEsImV4cCI6MTc3ODQ4MTMyOH0.4AvPGOTVFpv5UZu46xnZYI1FkLDJ-_YU7mZUtEXf8PA";
        // parser()：创建 JWT 解析器
        // verifyWith(...)：指定验签使用的密钥
        // build()：构建解析器对象
        // parseSignedClaims(...)：解析并校验 token
        // getPayload()：获取 JWT 中的载荷信息
        Claims body = Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();  //获取令牌信息
        System.out.println(body);
    }
}
