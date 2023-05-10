package com.travelproject.travelproject.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY= "1234";

    public String create(String email, String role) {
        
        //@ 현재시간으로부터 1시간 추가
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        // String id = "qwer";
        // String role = "";

        //@ JWT 생성과정
        String jwt 
                = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(expiredDate)
                    .claim("email", email)
                    .claim("role", role)
                    .compact();
        return jwt;
    }

    //@ JWT 검증
    public UserToken validate(String jwt) {
        Claims claims =
            Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();
        String email = (String) claims.get("email");
        String role = (String) claims.get("role");
        // System.out.println(id + "" + role);
        //@ ↑ 값을 확인할 수 있다.
        //@ 또한 파일하나 더 만들고 매개변수를 받아오고 반환 해주면 됨
        return new UserToken(email, role);
    }
    
}
