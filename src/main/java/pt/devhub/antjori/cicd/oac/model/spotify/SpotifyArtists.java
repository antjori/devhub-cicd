package pt.devhub.antjori.cicd.oac.model.spotify;

import java.util.List;

import lombok.Data;

/**
 * Mapping for Spotify's artists.
 */
@Data
public class SpotifyArtists {

    /**
     * A link to the Web API endpoint returning the full result of the request.
     */
    private String href;

    /**
     * The requested data.
     */
    List<Object> items;

    /**
     * The maximum number of items in the response (as set in the query or by default).
     */
    private int limit;

    /**
     * URL to the next page of items. (null if none)
     */
    private String next;

    /**
     * The offset of the items returned (as set in the query or by default).
     */
    private int offset;

    /**
     * URL to the previous page of items. (null if none)
     */
    private int previous;

    /**
     * The total number of items available to return.
     */
    private int total;
}
