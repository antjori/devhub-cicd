package pt.devhub.antjori.cicd.oac.model.spotify;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Mapping for Spotify's artist item.
 */
@Data
@JsonInclude(Include.NON_NULL)
public class SpotifyArtist {

    /**
     * Known external URLs for this album.
     */
    @JsonProperty(value = "external_urls")
    private List<ExternalUrl> externalUrls;

    /**
     * The followers of the artist.
     */
    private List<Follower> followers;

    /**
     * The genres the artist plays.
     */
    private List<String> genres;

    /**
     * A link to the Web API endpoint providing full details of the artist.
     */
    private String href;

    /**
     * The Spotify ID for the artist.
     */
    private String id;

    /**
     * The cover art for the artist in various sizes, widest first.
     */
    private List<Image> images;

    /**
     * The name of the artist.
     */
    private String name;

    /**
     * The level of popularity of the artist.
     */
    private int popularity;

    /**
     * The object type: 'artist'.
     */
    private String type;

    /**
     * The Spotify URI for the artist.
     */
    private String uri;

    /**
     * Mapping class for the followers of Spotify's artist item.
     */
    @Data
    @JsonInclude(Include.NON_NULL)
    private static final class Follower {

        /**
         * A link to the Web API endpoint providing full details of the followers.
         */
        private String href;

        /**
         * The amount of followers of the artist.
         */
        private int total;
    }

    /**
     * Mapping class for the image of Spotify's artist item. The cover art for the
     * album in various sizes, widest first.
     */
    @Data
    @JsonInclude(value = Include.NON_NULL)
    private static final class Image {

        /**
         * The image height in pixels. If unknown: null or not returned.
         */
        private int height;

        /**
         * The source URL of the image.
         */
        private String url;

        /**
         * The image width in pixels. If unknown: null or not returned.
         */
        private int width;
    }
}
