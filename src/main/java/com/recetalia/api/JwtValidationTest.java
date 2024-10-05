package com.recetalia.api;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;

public class JwtValidationTest {

    public static void main(String[] args) {
        String secret = "nSgDQMoPRNrLrCHZj6aWMEkJ6TfZAHYbGAefyLE2MlF3+zRoRJdJrYdgl8tgpvWHzOlPYz4Ly/KTFCayEImjmw==";
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqaG9tb3R0YSIsIm1haWwiOiJqaG9tb3R0YUBnbWFpbC5jb20iLCJyb2xlIjoiUk9MRV9NQU5BR0VNRU5UIiwiaWF0IjoxNzI4MDE0NTE3LCJleHAiOjE3MjgxMDA5MTd9.9y8bMEk8zFpowFcX_Mo4ccbWG0SQ04Qb6Y8RE-5xmfEsJRFwg2VK0EajK8Be43KoltecjuOZ95rqM7VePWdrvA";

        byte[] secretBytes = Base64.getDecoder().decode(secret);
        SecretKey secretKey = Keys.hmacShaKeyFor(secretBytes);
        System.out.println(secretKey.getAlgorithm());

        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            System.out.println("Token is valid");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Invalid token");
        }
    }
}
