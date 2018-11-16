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

    private String href;

    private String id;

    private String name;

    private String type;

    private String uri;
}
