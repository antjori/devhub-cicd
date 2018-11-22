package pt.devhub.antjori.cicd.oac.model.spotify;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Mapping for Spotify's album item.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
public class SpotifyAlbum extends SpotifyElement {

    /**
     * The type of the album: one of 'album', 'single', or 'compilation'.
     */
    @JsonProperty(value = "album_type")
    private String albumType;

    /**
     * The list of artists on the album.
     */
    private List<SpotifyArtist> artists;

    /**
     * The markets in which the album is available: ISO 3166-1 alpha-2 country
     * codes. Note that an album is considered available in a market when at least 1
     * of its tracks is available in that market.
     */
    @JsonProperty(value = "available_markets")
    private List<String> availableMarkets;

    /**
     * The copyright statements of the album.
     */
    private List<Copyright> copyrights;

    /**
     * The date when the album was released.
     */
    @JsonProperty(value = "release_date")
    private LocalDate releaseDate;

    /**
     * The precision of the release date.
     */
    @JsonProperty(value = "release_date_precision")
    private String releaseDatePrecision;

    /**
     * The amount of tracks on the album.
     */
    @JsonProperty(value = "total_tracks")
    private int totalTracks;

    /**
     * Mapping class for the copyright statements of the album.
     */
    @Data
    @ToString
    @JsonInclude(value = Include.NON_NULL)
    private static final class Copyright {

        /**
         * The copyright text for this album.
         */
        private String text;

        /**
         * The type of copyright: C = the copyright, P = the sound recording
         * (performance) copyright.
         */
        private String type;
    }
}
