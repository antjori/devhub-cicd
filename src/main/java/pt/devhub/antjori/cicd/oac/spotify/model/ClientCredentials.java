package pt.devhub.antjori.cicd.oac.spotify.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Mapping for Spotify's client credentials.
 */
@Data
public class ClientCredentials {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    private String scope;
}
