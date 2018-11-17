package pt.devhub.antjori.cicd.oac.model.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;

/**
 * Mapping for Spotify's search response.
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
public class SpotifySearchResponse {

    /**
     * Spotify's albums.
     */
    private SpotifyAlbums albums;

    /**
     * Spotify's artists.
     */
    private SpotifyArtists artists;

    /**
     * Spotify's tracks.
     */
    private SpotifyTracks tracks;

    /**
     * Spotify's error object.
     */
    private SpotifyError error;
}
