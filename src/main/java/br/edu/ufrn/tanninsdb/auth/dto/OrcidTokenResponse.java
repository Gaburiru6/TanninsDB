package br.edu.ufrn.tanninsdb.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OrcidTokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("orcid")
    private String orcid;

    private String name;

    @JsonProperty("token_type")
    private String tokenType;

    private String scope;

    @JsonProperty("expires_in")
    private Long expiresIn;
}
