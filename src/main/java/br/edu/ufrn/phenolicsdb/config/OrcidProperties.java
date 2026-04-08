package br.edu.ufrn.phenolicsdb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrcidProperties {

    @Value("${orcid.client-id}")
    private String clientId;

    @Value("${orcid.client-secret}")
    private String clientSecret;

    @Value("${orcid.redirect-uri}")
    private String redirectUri;

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}