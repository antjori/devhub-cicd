package pt.devhub.antjori.cicd.oac.spotify.model.track;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyElement;
import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbum;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtist;
import pt.devhub.antjori.cicd.oac.spotify.util.SpotifyElementType;

/**
 * Mapping for Spotify's track item.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
public class SpotifyTrack extends SpotifyElement {

    /**
     * The album to which this track belongs to.
     */
    private SpotifyAlbum album;

    /**
     * The artists who performed the track. Each artist object includes a link in
     * href to more detailed information about the artist.
     */
    private List<SpotifyArtist> artists;

    /**
     * A list of the countries in which the track can be played, identified by their
     * ISO 3166-1 alpha-2 code.
     */
    @JsonProperty(value = "available_markets")
    private List<String> availableMarkets;

    /**
     * The disc number (usually 1 unless the album consists of more than one disc).
     */
    @JsonProperty(value = "disc_number")
    private int discNumber;

    /**
     * The track length in milliseconds.
     */
    @JsonProperty(value = "duration_ms")
    private int durationMs;

    /**
     * Whether or not the track has explicit lyrics ( true = yes it does; false = no
     * it does not OR unknown).
     */
    private boolean explicit;

    /**
     * Known external IDs for the track.
     */
    @JsonProperty(value = "external_ids")
    private Map<String, String> externalIDs;

    /**
     * Part of the response when Track Relinking is applied. If true , the track is
     * playable in the given market. Otherwise false.
     */
    @JsonProperty(value = "is_playable")
    private boolean isPlayable;

    /**
     * Part of the response when Track Relinking is applied, and the requested track
     * has been replaced with different track. The track in the linked_from object
     * contains information about the originally requested track.
     */
    @JsonProperty(value = "linked_from")
    private TrackLink linkedFrom;

    /**
     * Part of the response when Track Relinking is applied, the original track is
     * not available in the given market, and Spotify did not have any tracks to
     * relink it with. The track response will still contain metadata for the
     * original track, and a restrictions object containing the reason why the track
     * is not available: {@code "restrictions" : {"reason" : "market"}}
     */
    private Map<String, String> restrictions;

    /**
     * The popularity of the track. The value will be between 0 and 100, with 100
     * being the most popular. The popularity of a track is a value between 0 and
     * 100, with 100 being the most popular. The popularity is calculated by
     * algorithm and is based, in the most part, on the total number of plays the
     * track has had and how recent those plays are. Generally speaking, songs that
     * are being played a lot now will have a higher popularity than songs that were
     * played a lot in the past. Duplicate tracks (e.g. the same track from a single
     * and an album) are rated independently. Artist and album popularity is derived
     * mathematically from track popularity. Note that the popularity value may lag
     * actual popularity by a few days: the value is not updated in real time.
     */
    private int popularity;

    /**
     * A link to a 30 second preview (MP3 format) of the track. Can be null
     */
    @JsonProperty(value = "preview_url")
    private String previewUrl;

    /**
     * The number of the track. If an album has several discs, the track number is
     * the number on the specified disc.
     */
    @JsonProperty(value = "track_number")
    private int trackNumber;

    /**
     * Whether or not the track is from a local file.
     */
    @JsonProperty(value = "is_local")
    private boolean isLocal;

    /**
     * Mapping for Spotify's linked from object.
     */
    @Data
    @ToString
    @JsonInclude(value = Include.NON_NULL)
    private static final class TrackLink {

        /**
         * Known external URLs for this track.
         */
        @JsonProperty(value = "external_urls")
        private Map<String, String> externalUrls;

        /**
         * A link to the Web API endpoint providing full details of the track.
         */
        private String href;

        /**
         * The Spotify ID for the track.
         */
        private String id;

        /**
         * The object type: “track”.
         */
        private String type;

        /**
         * The Spotify URI for the track.
         */
        private String uri;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getElementType() {
        return SpotifyElementType.TRACK.getType();
    }
}
