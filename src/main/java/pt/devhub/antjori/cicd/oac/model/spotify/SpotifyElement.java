package pt.devhub.antjori.cicd.oac.model.spotify;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

/**
 * Super class for all Spotify elements ({@link SpotifyAlbum} and
 * {@link SpotifyArtist}.
 */
@Data
@ToString
@JsonInclude(value = Include.NON_NULL)
public abstract class SpotifyElement {

    /**
     * Known external URLs for this album.
     */
    @JsonProperty(value = "external_urls")
    private ExternalUrls externalUrls;

    /**
     * A link to the Web API endpoint providing full details of the element.
     */
    private String href;

    /**
     * The Spotify ID for the element.
     */
    private String id;

    /**
     * The cover art for the element in various sizes, widest first.
     */
    private List<Image> images;

    /**
     * The name of the element.
     */
    private String name;

    /**
     * The object type. One of: 'album' or 'artist'.
     */
    private String type;

    /**
     * The Spotify URI for the artist.
     */
    private String uri;

    /**
     * Mapping class for the image of Spotify's artist item. The cover art for the
     * album in various sizes, widest first.
     */
    @Data
    @ToString
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
