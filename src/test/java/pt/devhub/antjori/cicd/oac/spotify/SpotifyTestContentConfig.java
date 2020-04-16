package pt.devhub.antjori.cicd.oac.spotify;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyElement.Image;

import java.util.List;

/**
 * The configuration for Spotify Web API.
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oac.spotify.test-content")
public class SpotifyTestContentConfig {

    /**
     * The list of available markets.
     */
    private List<String> availableMarkets;

    /**
     * The list of album images.
     */
    private List<Image> albumImages;

    /**
     * The list of genres.
     */
    private List<String> genres;

    /**
     * The list of artist images.
     */
    private List<Image> artistImages;
}
