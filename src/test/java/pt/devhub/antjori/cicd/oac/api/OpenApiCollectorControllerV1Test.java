package pt.devhub.antjori.cicd.oac.api;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import pt.devhub.antjori.cicd.oac.spotify.model.album.SpotifyAlbums;
import pt.devhub.antjori.cicd.oac.spotify.model.artist.SpotifyArtists;
import pt.devhub.antjori.cicd.oac.spotify.model.response.SpotifySearchResponse;
import pt.devhub.antjori.cicd.oac.spotify.model.track.SpotifyTracks;
import pt.devhub.antjori.cicd.oac.spotify.service.SpotifyService;

/**
 * Test class for {@link OpenApiCollectorControllerV1} where will be depicted
 * the classe's unit tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class OpenApiCollectorControllerV1Test {

    @InjectMocks
    private OpenApiCollectorControllerV1 oacController;

    @Mock
    private SpotifyService spotifyService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchSpotify() {
        // given
        SpotifySearchResponse spotifySearchResponse = new SpotifySearchResponse();
        spotifySearchResponse.setAlbums(new SpotifyAlbums());
        spotifySearchResponse.setArtists(new SpotifyArtists());
        spotifySearchResponse.setTracks(new SpotifyTracks());
        when(spotifyService.search(anyString(), anyString())).thenReturn(spotifySearchResponse);

        // when
        ResponseEntity<SpotifySearchResponse> response = oacController.searchSpotify("Eminem", "album,artist,track");

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getAlbums());
        assertNotNull(response.getBody().getArtists());
        assertNotNull(response.getBody().getTracks());
        verify(spotifyService, times(1)).search(anyString(), anyString());
    }

    @Test
    public void testSearchSpotify_withAlbums() {
        // given
        SpotifySearchResponse spotifySearchResponse = new SpotifySearchResponse();
        spotifySearchResponse.setAlbums(new SpotifyAlbums());
        when(spotifyService.search(anyString(), anyString())).thenReturn(spotifySearchResponse);

        // when
        ResponseEntity<SpotifySearchResponse> response = oacController.searchSpotify("Eminem", "album");

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getAlbums());
        assertNull(response.getBody().getArtists());
        assertNull(response.getBody().getTracks());
        verify(spotifyService, times(1)).search(anyString(), anyString());
    }

    @Test
    public void testSearchSpotify_withArtists() {
        // given
        SpotifySearchResponse spotifySearchResponse = new SpotifySearchResponse();
        spotifySearchResponse.setArtists(new SpotifyArtists());
        when(spotifyService.search(anyString(), anyString())).thenReturn(spotifySearchResponse);

        // when
        ResponseEntity<SpotifySearchResponse> response = oacController.searchSpotify("Eminem", "artist");

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNull(response.getBody().getAlbums());
        assertNotNull(response.getBody().getArtists());
        assertNull(response.getBody().getTracks());
        verify(spotifyService, times(1)).search(anyString(), anyString());
    }

    @Test
    public void testSearchSpotify_withTracks() {
        // given
        SpotifySearchResponse spotifySearchResponse = new SpotifySearchResponse();
        spotifySearchResponse.setTracks(new SpotifyTracks());
        when(spotifyService.search(anyString(), anyString())).thenReturn(spotifySearchResponse);

        // when
        ResponseEntity<SpotifySearchResponse> response = oacController.searchSpotify("Eminem", "track");

        // then
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertNull(response.getBody().getAlbums());
        assertNull(response.getBody().getArtists());
        assertNotNull(response.getBody().getTracks());
        verify(spotifyService, times(1)).search(anyString(), anyString());
    }
}
