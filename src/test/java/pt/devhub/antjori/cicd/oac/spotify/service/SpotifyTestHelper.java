package pt.devhub.antjori.cicd.oac.spotify.service;

import java.util.ArrayList;
import java.util.List;

import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbum;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtist;

/**
 * Helper class to assist the tests of Spotify web API.
 */
public final class SpotifyTestHelper {

    public static final SpotifyAlbum createSpotifyAlbum() {
        SpotifyAlbum album = new SpotifyAlbum();

        album.setType("album");
        album.setArtists(createSpotifyArtists());

        return album;
    }

    public static final List<SpotifyArtist> createSpotifyArtists() {
        List<SpotifyArtist> artists = new ArrayList<>();

        return artists;
    }
}
