package pt.devhub.antjori.cicd.oac.model.spotify;

import lombok.Data;

/**
 * Mapping for Spotify's error.
 */
@Data
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
