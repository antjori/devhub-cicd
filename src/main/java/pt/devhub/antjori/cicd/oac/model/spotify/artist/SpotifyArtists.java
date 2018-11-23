package pt.devhub.antjori.cicd.oac.model.spotify.artist;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pt.devhub.antjori.cicd.oac.model.spotify.SpotifyPagingObject;

/**
 * Mapping for Spotify's artists.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
public class SpotifyArtists extends SpotifyPagingObject<SpotifyArtist> {

}
