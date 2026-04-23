package br.edu.ufrn.tanninsdb.auth.service;

import br.edu.ufrn.tanninsdb.config.OrcidProperties;
import br.edu.ufrn.tanninsdb.auth.dto.OrcidTokenResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class OrcidService {

    private final OrcidProperties properties;
    private final RestTemplate restTemplate = new RestTemplate();

    public OrcidService(OrcidProperties properties) {
        this.properties = properties;
    }

    public OrcidTokenResponse exchangeCodeForToken(String code) {

        String tokenUrl = "https://orcid.org/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", properties.getClientId());
        body.add("client_secret", properties.getClientSecret());
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("redirect_uri", properties.getRedirectUri());

        HttpEntity<MultiValueMap<String, String>> request =
                new HttpEntity<>(body, headers);

        ResponseEntity<OrcidTokenResponse> response =
                restTemplate.postForEntity(tokenUrl, request, OrcidTokenResponse.class);

        return response.getBody();
    }
}