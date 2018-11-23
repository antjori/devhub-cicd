package pt.devhub.antjori.cicd.oac.model.spotify.album;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pt.devhub.antjori.cicd.oac.model.spotify.SpotifyPagingObject;

/**
 * Mapping for Spotify's albums.
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
@JsonInclude(value = Include.NON_NULL)
public class SpotifyAlbums extends SpotifyPagingObject<SpotifyAlbum> {

}
