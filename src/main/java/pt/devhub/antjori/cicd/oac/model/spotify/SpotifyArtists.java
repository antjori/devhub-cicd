package pt.devhub.antjori.cicd.oac.model.spotify;

import java.util.List;

import lombok.Data;

/**
 * Mapping for Spotify's artists.
 */
@Data
public class SpotifyArtists {

    private String href;

    List<Object> items;

    private int limit;

    private int offset;

    private int previous;

    private int total;
}
