package pt.devhub.antjori.cicd.oac.spotify.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyElement.Image;
import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbum;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtist;

/**
 * Helper class to assist the tests of Spotify web API.
 */
public final class SpotifyTestHelper {

    @Value(value = "${oac.spotify.testContent.availableMarkets}")
    private List<String> availableMarkets;

    @Value(value = "${oac.spotify.testContent.images}")
    private List<Image> images;

    /**
     * Creates an instance of a {@link SpotifyAlbum} object.
     * 
     * @return an instance of a {@link SpotifyAlbum} object
     */
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

    /**
     * Creates a {@link List} containing at least an instance of
     * {@link SpotifyArtist} object.
     * 
     * @return a {@link List} containing at least an instance of
     *         {@link SpotifyArtist} object
     */
    public final List<SpotifyArtist> createSpotifyArtists() {
        List<SpotifyArtist> artists = new ArrayList<>();

        return artists;
    }

    /**
     * Creates a {@link List} containing at least a {@link String} representation of
     * an available market.
     * 
     * @return a {@link List} containing at least a {@link String} representation of
     *         an available market
     */
    public final List<String> createAvailableMarkets() {
        return this.availableMarkets;
    }

    /**
     * Creates a key-value {@link Map} of {@link String} representing the external
     * URLs.
     * 
     * @return a key-value {@link Map} of {@link String} representing the external
     *         URLs
     */
    public final Map<String, String> createExternalUrls() {
        Map<String, String> externalUrls = new HashMap<>();

        externalUrls.put("spotify", "https://open.spotify.com/album/3HNnxK7NgLXbDoxRZxNWiR");

        return externalUrls;
    }

    /**
     * Creates a {@link List} containing at least an instance of {@link Image}
     * object.
     * 
     * @return a {@link List} containing at least an instance of {@link Image}
     *         object
     */
    public final List<Image> createImages() {
        return this.images;
    }
}
