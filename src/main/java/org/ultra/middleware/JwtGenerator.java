package org.ultra.middleware;


import io.jsonwebtoken.Jwts;

import java.security.interfaces.RSAPrivateKey;
import java.time.Instant;
import java.util.Date;


public class JwtGenerator {

    public static final String ISSUER = "PETERS-AUTH-MANAGER";

    // Run this class to print a demo token using the bundled development key.
    public static void main(String[] args) throws Exception {
        System.out.println(generateToken(new AuthConfig().privateKey()));
    }

    public static String generateToken(RSAPrivateKey privateKey) {

        Instant now = Instant.now();

        return Jwts.builder()
                .subject("FAKE-DEVICE")
                .issuer(ISSUER)
                .audience()
                .add("PETERDCODES-OFFICIAL-SERVICES")
                .and()
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(900)))
                .signWith(privateKey, Jwts.SIG.RS256)
                .compact();
    }
}
