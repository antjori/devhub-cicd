package pt.devhub.antjori.cicd.oac.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import pt.devhub.antjori.cicd.oac.model.Credentials;
import pt.devhub.antjori.cicd.oac.model.Url;

/**
 * The configuration for Spotify Web API.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oac.spotify")
public class SpotifyConfig {

    /**
     * The Spotify web API credentials.
     */
    private Credentials credentials;

    /**
     * The Spotify web API token URL.
     */
    private Url tokenUrl;

    /**
     * The Spotify web API search URL.
     */
    private Url searchUrl;
}
