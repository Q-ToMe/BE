package com.example.qtome_be.config;

import com.example.qtome_be.config.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenExtractor {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String extractToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        String token;
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            token= bearerToken.substring(7);
            return jwtTokenUtil.getUid(token);
        }
        return null;
    }
}