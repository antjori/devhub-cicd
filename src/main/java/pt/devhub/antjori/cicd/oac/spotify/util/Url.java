package pt.devhub.antjori.cicd.oac.spotify.util;

import org.springframework.http.HttpMethod;

import lombok.Data;

/**
 * Class that houses the web APIs URL configuration.
 */
@Data
public class Url {

    /**
     * The type of the request method.
     */
    private HttpMethod type;

    /**
     * The web API url.
     */
    private String url;
}
