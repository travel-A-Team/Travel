package com.travelproject.travelproject.provider;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.travelproject.travelproject.common.constant.ResponseMessage;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    public String create(String email, String role) {
        
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(simpleDateFormat.format(expiredDate));

        // String id = "qwer";
        // String role = "";

        //@ JWT 생성과정
        String jwt = 
            Jwts.builder()
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

        String email = null;
        String role = null;

        try {
            Claims claims =
            Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();

            email = (String) claims.get("email");
            role = (String) claims.get("role");
            
        } catch (Exception exception) {
            return null;
        }



        return new UserToken(email, role);
    }
    
}
