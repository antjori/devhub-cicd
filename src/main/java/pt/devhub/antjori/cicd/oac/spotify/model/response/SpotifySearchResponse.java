package pt.devhub.antjori.cicd.oac.spotify.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyError;
import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbums;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtists;
import pt.devhub.antjori.cicd.oac.spotify.model.track.SpotifyTracks;

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
