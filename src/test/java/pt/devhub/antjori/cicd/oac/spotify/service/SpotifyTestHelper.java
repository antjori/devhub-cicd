package pt.devhub.antjori.cicd.oac.spotify.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyElement;
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

        album.setAlbumType("album");
        album.setArtists(createSpotifyArtists());
        album.setAvailableMarkets(createAvailableMarkets());
        album.setExternalUrls(createExternalUrls());
        album.setHref("https://api.spotify.com/v1/albums/3HNnxK7NgLXbDoxRZxNWiR");
        album.setId("3HNnxK7NgLXbDoxRZxNWiR");
        album.setImages(createImages());
        album.setName("Kamikaze");
        album.setReleaseDate(LocalDate.now());
        album.setReleaseDatePrecision("day");
        album.setTotalTracks(13);
        album.setType("album");
        album.setUri("spotify:album:3HNnxK7NgLXbDoxRZxNWiR");

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

    public final List<SpotifyElement.Image> createImages() {
        List<SpotifyElement.Image> images = new ArrayList<>();

        return images;
    }
}
