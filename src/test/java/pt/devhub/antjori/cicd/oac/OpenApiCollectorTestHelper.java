package pt.devhub.antjori.cicd.oac;

import org.springframework.beans.factory.annotation.Autowired;

import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbums;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtists;
import pt.devhub.antjori.cicd.oac.spotify.model.track.SpotifyTracks;
import pt.devhub.antjori.cicd.oac.spotify.service.SpotifyTestHelper;

/**
 * Helper class to assist the integration tests of OAC.
 */
public final class OpenApiCollectorTestHelper {

    @Autowired
    private SpotifyTestHelper testHelper;

    /**
     * Creates an instance of a {@link SpotifyAlbums} object.
     * 
     * @return an instance of a {@link SpotifyAlbums} object
     */
    public SpotifyAlbums createSpotifyAlbums() {
        return this.testHelper.createSpotifyAlbums();
    }

    /**
     * Creates an instance of a {@link SpotifyArtists} object.
     * 
     * @return an instance of a {@link SpotifyArtists} object
     */
    public SpotifyArtists createSpotifyArtists() {
        return this.testHelper.createSpotifyArtists();
    }

    /**
     * Creates an instance of a {@link SpotifyTracks} object.
     * 
     * @return an instance of a {@link SpotifyTracks} object
     */
    public SpotifyTracks createSpotifyTracks() {
        return this.testHelper.createSpotifyTracks();
    }
}
