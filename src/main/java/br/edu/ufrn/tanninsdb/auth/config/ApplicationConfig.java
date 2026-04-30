package br.edu.ufrn.tanninsdb.auth.config;

import br.edu.ufrn.tanninsdb.usuario.repository.UsuarioRepository;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    private final UsuarioRepository userRepository;

    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        SecretKey spec = new SecretKeySpec(keyBytes, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(spec).build();
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        JWKSource<SecurityContext> jwks = new ImmutableSecret<>(keyBytes);
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return orcidId -> userRepository.findByIdOrcid(orcidId)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}