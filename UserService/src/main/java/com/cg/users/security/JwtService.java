package com.cg.users.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${apiKey}")
    private String secretkey;
    // generate from this link : https://jwtsecret.com/generate

    //First step get Signing key
    private Key getSigningKey(){
        byte[] keyBytes= Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
        //Hash-based Message Authentication Code
    }

    //Second step to generate token
    public  String generateToken(UserDetails user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("authorities",populateAuthorities(user.getAuthorities()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> authoritySet=new HashSet<>();
        for(GrantedAuthority authority: authorities) {
            authoritySet.add(authority.getAuthority());
        }
        return String.join(",", authoritySet);
    }

    private Claims extractAllCliams(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
        final Claims claims=extractAllCliams(token);
        return claimResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName=extractUsername(token);
        return userName.equals(userDetails.getUsername());
    }

}
