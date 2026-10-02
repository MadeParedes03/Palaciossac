package com.project.Palaciossac.customer.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TokenService {

    private final JwtEncoder encoder;
    private final long expirationSeconds;

    public TokenService(JwtEncoder encoder,
                        @Value("${jwt.expiration-seconds:3600}") long expirationSeconds) {
        this.encoder = encoder;
        this.expirationSeconds = expirationSeconds;
    }

    public long getExpirationSeconds() {
        return expirationSeconds;
    }

    // Generate tokens
    public String generate(Authentication auth) {
        // timestamp
        Instant now = Instant.now();

        // payload { "iss": "palaciossac-api", "exp": "14134124412", ... }
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("palaciossac-api")
                .subject(auth.getName())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirationSeconds))
                .claim("roles", roles(auth))
                .build();

        // header
        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        return encoder.encode(
                JwtEncoderParameters.from(
                        header,
                        claims
                )
        ).getTokenValue();
    }

    // Extract real role names and remove the `ROLE_` prefix
    private List<String> roles(Authentication auth) {
        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.replace("ROLE_", "")) // ROLE_USER -> USER // ROLE_ADMIN -> ADMIN
                .toList();
    }

}
