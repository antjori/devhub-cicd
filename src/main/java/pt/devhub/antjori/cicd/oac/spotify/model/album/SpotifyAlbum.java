package pt.devhub.antjori.cicd.oac.spotify.model.album;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyElement;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtist;
import pt.devhub.antjori.cicd.oac.spotify.model.track.SpotifyTrack;
import pt.devhub.antjori.cicd.oac.spotify.util.SpotifyElementType;

/**
 * Mapping for Spotify's album item.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
public class SpotifyAlbum extends SpotifyElement {

    /**
     * The field is present when getting an artist’s albums. Possible values are
     * “album”, “single”, “compilation”, “appears_on”. Compare to album_type this
     * field represents relationship between the artist and the album.
     */
    @JsonProperty(value = "album_group")
    private String albumGroup;

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
     * Known external IDs for the album.
     */
    @JsonProperty(value = "external_ids")
    private Map<String, String> externalIDs;

    /**
     * A list of the genres used to classify the album. For example: "Prog Rock",
     * "Post-Grunge". (If not yet classified, the array is empty.)
     */
    private List<String> genres;

    /**
     * The label for the album.
     */
    private String label;

    /**
     * The popularity of the album. The value will be between 0 and 100, with 100
     * being the most popular. The popularity is calculated from the popularity of
     * the album’s individual tracks.
     */
    private int popularity;

    /**
     * The date the album was first released, for example 1981. Depending on the
     * precision, it might be shown as 1981-12 or 1981-12-15.
     */
    @JsonProperty(value = "release_date")
    private LocalDate releaseDate;

    /**
     * The precision with which release_date value is known: year, month or day.
     */
    @JsonProperty(value = "release_date_precision")
    private String releaseDatePrecision;

    /**
     * Part of the response when Track Relinking is applied, the original track is
     * not available in the given market, and Spotify did not have any tracks to
     * relink it with. The track response will still contain metadata for the
     * original track, and a restrictions object containing the reason why the track
     * is not available: {@code "restrictions" : {"reason" : "market"}}
     */
    private Map<String, String> restrictions;

    /**
     * The amount of tracks on the album.
     */
    @JsonProperty(value = "total_tracks")
    private int totalTracks;

    /**
     * The tracks of the album.
     */
    private List<SpotifyTrack> tracks;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getElementType() {
        return SpotifyElementType.ALBUM.getType();
    }
}
