package com.courseportal.coursebackend.security;

//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    //private final String SECRET = "mysecretkey";
//    private final String SECRET = "mysecretkeymysecretkeymysecretkey12";
//    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
//
//    // ✅ Generate Token
//    public String generateToken(String email) {
//
//        return Jwts.builder()
//                .setSubject(email) // store user email
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day
//                //.signWith(SignatureAlgorithm.HS256, SECRET)
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // ✅ Extract Email from Token
//    public String extractEmail(String token) {
//        return getClaims(token).getSubject();
//    }
//
//    // ✅ Validate Token
//    public boolean validateToken(String token, String email) {
//        return extractEmail(token).equals(email) && !isTokenExpired(token);
//    }
//
//    // 🔹 Helper Methods
//
//    private Claims getClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET)
//                .parseClaimsJws(token)
//                .getBody();
//    }
//
//    private boolean isTokenExpired(String token) {
//        return getClaims(token).getExpiration().before(new Date());
//    }
//}


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET =
            "mysecretkeymysecretkeymysecretkey123"; // ✅ 32+ chars

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

//    public String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
public String generateToken(String email, String role) {

    return Jwts.builder()
            .setSubject(email)
            .claim("role", role) // ✅ include role
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000))
            .signWith(key)
            .compact();
}

    public String extractEmail(String token) {
        return getClaims(token).getSubject();
    }

    public boolean validateToken(String token, String email) {
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}