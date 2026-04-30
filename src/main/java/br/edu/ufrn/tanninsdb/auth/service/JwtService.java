package br.edu.ufrn.tanninsdb.auth.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${security.jwt.expiration-time:3600000}")
    @Getter
    private long jwtExpiration;

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    public String extractUsername(String token) {
        try {
            return this.decoder.decode(token).getSubject();
        } catch (JwtException e) {
            return null;
        }
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(Map.of(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        Instant now = Instant.now();
        
        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusMillis(jwtExpiration))
                .subject(userDetails.getUsername())
                .claims(c -> extraClaims.forEach(c::put))
                .build();

        return this.encoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            Jwt jwt = this.decoder.decode(token);
            return (jwt.getSubject().equals(userDetails.getUsername())) && !isTokenExpired(jwt);
        } catch (JwtException e) {
            return false;
        }
    }

    private boolean isTokenExpired(Jwt jwt) {
        return jwt.getExpiresAt().isBefore(Instant.now());
    }
}
