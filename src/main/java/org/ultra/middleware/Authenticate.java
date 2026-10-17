package org.ultra.middleware;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.interfaces.RSAPublicKey;

@Component
public class Authenticate extends OncePerRequestFilter {

    private final JwtParser jwtParser;

    //The RSAPublicKey needed will be a 'bean' managed by spring
    public Authenticate(RSAPublicKey publicKey) {
        this.jwtParser = Jwts.parser()
                .verifyWith(publicKey)
                .requireIssuer(JwtGenerator.ISSUER)
                .build();
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {

        String auth = request.getHeader("Authorization");

        if (auth == null || !auth.startsWith("Bearer ")) {
            response.sendError(401, "Missing bearer token");
            return;
        }

        try {
            String token = auth.substring(7);

            Jws<Claims> jwt = jwtParser.parseSignedClaims(token);

            String deviceId = jwt.getPayload().getSubject();

            request.setAttribute("deviceId", deviceId);

        } catch (JwtException | IllegalArgumentException e) {
            response.sendError(401, "Invalid or expired token");
            return;
        }

        chain.doFilter(request, response);
    }
}
