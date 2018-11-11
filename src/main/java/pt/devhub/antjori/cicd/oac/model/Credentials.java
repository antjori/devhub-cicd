package pt.devhub.antjori.cicd.oac.model;

import lombok.Data;

/**
 * Class that houses the credentials to access web APIs.
 */
@Data
public class Credentials {

    /**
     * The web API client identifier.
     */
    private String clientId;

    /**
     * The web API client secret.
     */
    private String clientSecret;
}
