package com.example.jwttest.config;

import com.sun.org.apache.xpath.internal.operations.Bool;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtProvider {


    private String SECRET = "secret";

    public JwtProvider(String SECRET) {
        this.SECRET = SECRET;
    }

    public JwtProvider() {
    }

    Long expiredate = 60*1000*60*2L;

    public String generate(HashMap<String, Object> claims, String username) {

        return Jwts
                .builder()
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiredate))
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public Boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date());
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    public Boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public String getSubject(String token) {
        try{

            String subject = Jwts
                    .parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            return subject;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
