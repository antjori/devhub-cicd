package pt.devhub.antjori.cicd.oac.model.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Mapping for Spotify's artist item.
 */
@Data
@JsonInclude(Include.NON_NULL)
public class SpotifyArtist {

}
