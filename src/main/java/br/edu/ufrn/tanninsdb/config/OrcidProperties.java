package br.edu.ufrn.tanninsdb.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OrcidProperties {

    @Value("${orcid.client-id}")
    private String clientId;

    @Value("${orcid.client-secret}")
    private String clientSecret;

    @Value("${orcid.redirect-uri}")
    private String redirectUri;
}
