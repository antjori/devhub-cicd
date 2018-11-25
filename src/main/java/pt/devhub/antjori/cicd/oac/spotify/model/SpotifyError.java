package pt.devhub.antjori.cicd.oac.spotify.model;

import lombok.Data;
import lombok.ToString;

/**
 * Mapping for Spotify's error.
 */
@Data
@ToString
public class SpotifyError {

    /**
     * The status of the error.
     */
    private int status;

    /**
     * The message of the error.
     */
    private String message;
}
