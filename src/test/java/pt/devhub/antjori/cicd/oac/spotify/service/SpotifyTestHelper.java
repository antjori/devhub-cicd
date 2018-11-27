package pt.devhub.antjori.cicd.oac.spotify.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import lombok.extern.slf4j.Slf4j;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyElement;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyElement.Image;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyPagingObject;
import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbum;
import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbums;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtist;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtists;
import pt.devhub.antjori.cicd.oac.spotify.model.track.SpotifyTrack;
import pt.devhub.antjori.cicd.oac.spotify.model.track.SpotifyTracks;

/**
 * Helper class to assist the tests of Spotify web API.
 */
@Slf4j
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
        album.setArtists(createSpotifyArtistList());
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
    public final List<SpotifyArtist> createSpotifyArtistList() {
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

    /**
     * Creates an instance of a {@link SpotifyAlbums} object.
     * 
     * @return an instance of a {@link SpotifyAlbums} object
     */
    public SpotifyAlbums createSpotifyAlbums() {
        List<SpotifyAlbum> items = new ArrayList<>();
        items.add(createSpotifyAlbum());

        SpotifyAlbums albums = new SpotifyAlbums();

        albums.setHref("https://api.spotify.com/v1/search?query=Eminem&type=album&offset=0&limit=20");
        albums.setItems(items);
        albums.setLimit(20);
        albums.setNext("https://api.spotify.com/v1/search?query=Eminem&type=album&offset=20&limit=20");
        albums.setOffset(0);
        albums.setPrevious(null);
        albums.setTotal(549);

        return albums;
    }

    /**
     * Creates an instance of a {@link SpotifyArtists} object.
     * 
     * @return an instance of a {@link SpotifyArtists} object
     */
    public SpotifyArtists createSpotifyArtists() {
        return createSpotifyElement(SpotifyArtists.class, SpotifyArtist.class);
    }

    /**
     * Creates an instance of a {@link SpotifyTracks} object.
     * 
     * @return an instance of a {@link SpotifyTracks} object
     */
    public SpotifyTracks createSpotifyTracks() {
        return createSpotifyElement(SpotifyTracks.class, SpotifyTrack.class);
    }

    private <S extends SpotifyPagingObject<T>, T extends SpotifyElement> S createSpotifyElement(final Class<S> sClass,
            final Class<T> tClass) {
        S pagingObj = null;

        try {
            List<T> items = new ArrayList<>();
            T element = createElement(tClass);
            items.add(element);

            pagingObj = sClass.newInstance();
            pagingObj.setHref("https://api.spotify.com/v1/search?query=Eminem&type=" + element.getElementType()
                    + "&offset=0&limit=20");
            pagingObj.setItems(items);
            pagingObj.setLimit(20);
            pagingObj.setNext(null);
            pagingObj.setOffset(0);
            pagingObj.setPrevious(null);
            pagingObj.setTotal(-1);
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("An error occurred while creating a Spotify element", e);
        }

        return pagingObj;
    }

    @SuppressWarnings("unchecked")
    private <T extends SpotifyElement> T createElement(final Class<T> tClass) {
        T element = null;

        if (tClass.isAssignableFrom(SpotifyAlbum.class)) {
            element = (T) createSpotifyAlbum();
        } else if (tClass.isAssignableFrom(SpotifyArtist.class)) {
            element = null;
        } else {
            element = null;
        }

        return element;
    }
}
