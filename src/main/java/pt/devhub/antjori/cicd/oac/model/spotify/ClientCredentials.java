package pt.devhub.antjori.cicd.oac.model.spotify;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Spotify's client credentials.
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
