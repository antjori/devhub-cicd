package pt.devhub.antjori.cicd.oac.model.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Mapping for Spotify's search response.
 */
@Data
@JsonInclude(Include.NON_NULL)
public class SpotifySearchResponse {

    /**
     * Spotify's artists.
     */
    private SpotifyArtists artists;

    /**
     * Spotify's error object.
     */
    private SpotifyError error;
}
