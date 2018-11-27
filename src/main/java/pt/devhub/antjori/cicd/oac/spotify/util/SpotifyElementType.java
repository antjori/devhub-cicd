package pt.devhub.antjori.cicd.oac.spotify.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The available types of Spotify elements.
 */
@Getter
@AllArgsConstructor
public enum SpotifyElementType {

    ALBUM("album"), ARTIST("artist"), TRACK("track");

    // The Spotify element type
    private String type;
}
