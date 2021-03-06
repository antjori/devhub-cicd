package pt.devhub.antjori.cicd.oac.spotify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyElement;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyElement.Image;
import pt.devhub.antjori.cicd.oac.spotify.model.SpotifyPagingObject;
import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbum;
import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbums;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtist;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtist.Followers;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtists;
import pt.devhub.antjori.cicd.oac.spotify.model.track.SpotifyTrack;
import pt.devhub.antjori.cicd.oac.spotify.model.track.SpotifyTracks;
import pt.devhub.antjori.cicd.oac.spotify.util.SpotifyElementType;

/**
 * Helper class to assist the tests of Spotify web API.
 */
@Slf4j
@Component
public final class SpotifyTestHelper {

    @Autowired
    private SpotifyTestContentConfig testContentConfig;

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
        album.setExternalUrls(createExternalUrls(SpotifyElementType.ALBUM));
        album.setHref("https://api.spotify.com/v1/albums/3HNnxK7NgLXbDoxRZxNWiR");
        album.setId("3HNnxK7NgLXbDoxRZxNWiR");
        album.setImages(createImages(SpotifyElementType.ALBUM));
        album.setName("Kamikaze");
        album.setReleaseDate(LocalDate.now());
        album.setReleaseDatePrecision("day");
        album.setTotalTracks(13);
        album.setType(SpotifyElementType.ALBUM.getType());
        album.setUri("spotify:album:3HNnxK7NgLXbDoxRZxNWiR");

        return album;
    }

    /**
     * Creates an instance of a {@link SpotifyArtist} object.
     * 
     * @return an instance of a {@link SpotifyArtist} object
     */
    public final SpotifyArtist createSpotifyArtist() {
        SpotifyArtist artist = new SpotifyArtist();

        artist.setExternalUrls(createExternalUrls(SpotifyElementType.ARTIST));
        artist.setFollowers(createFollowers());
        artist.setGenres(this.testContentConfig.getGenres());
        artist.setHref("https://api.spotify.com/v1/artists/7dGJo4pcD2V6oG8kP0tJRR");
        artist.setId("7dGJo4pcD2V6oG8kP0tJRR");
        artist.setImages(createImages(SpotifyElementType.ARTIST));
        artist.setName("Eminem");
        artist.setPopularity(96);
        artist.setType(SpotifyElementType.ARTIST.getType());
        artist.setUri("spotify:artist:7dGJo4pcD2V6oG8kP0tJRR");

        return artist;
    }

    /**
     * Creates an instance of {@link Followers} object.
     * 
     * @return an instance of {@link Followers} object
     */
    private final Followers createFollowers() {
        Followers followers = new Followers();

        followers.setHref(null);
        followers.setTotal(22050143);

        return followers;
    }

    /**
     * Creates an instance of a {@link SpotifyTrack} object.
     * 
     * @return an instance of a {@link SpotifyTrack} object
     */
    public final SpotifyTrack createSpotifyTrack() {
        SpotifyTrack track = new SpotifyTrack();

        track.setAlbum(createSpotifyAlbum());
        track.setArtists(createSpotifyArtistList());
        track.setAvailableMarkets(createAvailableMarkets());
        track.setDiscNumber(1);
        track.setDurationMs(244679);
        track.setExplicit(true);
        Map<String, String> externalIds = new HashMap<>();
        externalIds.put("isrc", "USUM71813334");
        track.setExternalIDs(externalIds);
        track.setExternalUrls(createExternalUrls(SpotifyElementType.TRACK));
        track.setHref("https://api.spotify.com/v1/tracks/60SdxE8apGAxMiRrpbmLY0");
        track.setId("60SdxE8apGAxMiRrpbmLY0");
        track.setLocal(false);
        track.setName("Lucky You (feat. Joyner Lucas)");
        track.setPopularity(90);
        track.setPreviewUrl(null);
        track.setTrackNumber(3);
        track.setType(SpotifyElementType.TRACK.getType());
        track.setUri("spotify:track:60SdxE8apGAxMiRrpbmLY0");

        return track;
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

        artists.add(createSpotifyArtist());

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
        return this.testContentConfig.getAvailableMarkets();
    }

    /**
     * Creates a key-value {@link Map} of {@link String} representing the external
     * URLs.
     * 
     * @return a key-value {@link Map} of {@link String} representing the external
     *         URLs
     */
    public final Map<String, String> createExternalUrls(final SpotifyElementType spotifyElementType) {
        Map<String, String> externalUrls = new HashMap<>();
        String externalUrl = StringUtils.EMPTY;

        switch (spotifyElementType) {
        case ALBUM:
            externalUrl = "https://open.spotify.com/album/3HNnxK7NgLXbDoxRZxNWiR";
            break;
        case ARTIST:
            externalUrl = "https://open.spotify.com/artist/7dGJo4pcD2V6oG8kP0tJRR";
            break;
        case TRACK:
            externalUrl = "https://open.spotify.com/track/60SdxE8apGAxMiRrpbmLY0";
            break;
        default:
            break;
        }

        externalUrls.put("spotify", externalUrl);

        return externalUrls;
    }

    /**
     * Creates a {@link List} containing at least an instance of {@link Image}
     * object.
     * 
     * @return a {@link List} containing at least an instance of {@link Image}
     *         object
     */
    public final List<Image> createImages(final SpotifyElementType spotifyElementType) {
        List<Image> images = null;

        switch (spotifyElementType) {
        case ALBUM:
            images = this.testContentConfig.getAlbumImages();
            break;
        case ARTIST:
            images = this.testContentConfig.getArtistImages();
            break;
        case TRACK:
            break;
        default:
            break;
        }

        return images;
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
                    + "&offset=20&limit=20");
            pagingObj.setItems(items);
            pagingObj.setLimit(20);
            pagingObj.setNext("https://api.spotify.com/v1/search?query=Eminem&type=" + element.getElementType()
                    + "&offset=40&limit=20");
            pagingObj.setOffset(0);
            pagingObj.setPrevious("https://api.spotify.com/v1/search?query=Eminem&type=" + element.getElementType()
                    + "&offset=0&limit=20");
            pagingObj.setTotal(Short.MAX_VALUE);
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
            element = (T) createSpotifyArtist();
        } else {
            element = (T) createSpotifyTrack();
        }

        return element;
    }
}
