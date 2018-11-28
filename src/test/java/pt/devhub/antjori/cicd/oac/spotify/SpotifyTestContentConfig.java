package pt.devhub.antjori.cicd.oac.spotify;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyElement.Image;

/**
 * The configuration for Spotify Web API.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oac.spotify.testContent")
public class SpotifyTestContentConfig {

    private List<String> availableMarkets;

    private List<Image> images;
}
