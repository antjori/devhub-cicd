package pt.devhub.antjori.cicd.oac.spotify.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbum;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtist;

/**
 * Helper class to assist the tests of Spotify web API.
 */
public final class SpotifyTestHelper {

    @Value(value = "${oac.spotify.availableMarkets}")
    private List<String> availableMarkets;

    public final SpotifyAlbum createSpotifyAlbum() {
        SpotifyAlbum album = new SpotifyAlbum();

        album.setType("album");
        album.setArtists(createSpotifyArtists());
        album.setAvailableMarkets(createAvailableMarkets());
        album.setExternalUrls(createExternalUrls());

        return album;
    }

    public final List<SpotifyArtist> createSpotifyArtists() {
        List<SpotifyArtist> artists = new ArrayList<>();

        return artists;
    }

    public final List<String> createAvailableMarkets() {
        return this.availableMarkets;
    }

    public final Map<String, String> createExternalUrls() {
        Map<String, String> externalUrls = new HashMap<>();

        externalUrls.put("spotify", "https://open.spotify.com/album/3HNnxK7NgLXbDoxRZxNWiR");

        return externalUrls;
    }
}
