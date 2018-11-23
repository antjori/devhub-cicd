package pt.devhub.antjori.cicd.oac.model.spotify;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Mapping for Spotify's artist item.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
public class SpotifyArtist extends SpotifyElement {

    /**
     * The followers of the artist.
     */
    private Followers followers;

    /**
     * A list of the genres the artist is associated with. For example: "Prog Rock"
     * , "Post-Grunge". (If not yet classified, the array is empty.)
     */
    private List<String> genres;

    /**
     * The level of popularity of the artist.
     */
    private int popularity;

    /**
     * Mapping class for the followers of Spotify's artist item.
     */
    @Data
    @ToString
    @JsonInclude(value = Include.NON_NULL)
    private static final class Followers {

        /**
         * A link to the Web API endpoint providing full details of the followers.
         */
        private String href;

        /**
         * The amount of followers of the artist.
         */
        private int total;
    }
}
