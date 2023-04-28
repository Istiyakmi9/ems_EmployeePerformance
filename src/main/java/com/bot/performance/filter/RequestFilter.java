package com.bot.performance.filter;

import com.bot.performance.model.CurrentUserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class RequestFilter implements Filter {

    @Autowired
    CurrentUserDetail userDetail;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String headerToken = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(headerToken == null || !headerToken.startsWith("Bearer")) {
            throw new RuntimeException("Invalid toke.");
        }

        headerToken = headerToken.substring(7);
        try {
            String secret = "SchoolInMind_secret_key_is__bottomhalf@mi9_01";
            byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
            SecretKey key = Keys.hmacShaKeyFor(keyBytes);

            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(headerToken)
                    .getBody();

            String email = claims.get("email", String.class);
            String sid = claims.get("sid", String.class);
            userDetail.setEmail(email);
            userDetail.setUserId(Long.parseLong(sid));
        } catch (ExpiredJwtException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Your session got expired");
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Unauthorized access. Please try with valid token.");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
