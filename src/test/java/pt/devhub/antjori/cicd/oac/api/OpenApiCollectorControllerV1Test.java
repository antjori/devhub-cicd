package pt.devhub.antjori.cicd.oac.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import pt.devhub.antjori.cicd.oac.spotify.model.response.SpotifySearchResponse;
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
        // MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchSpotify() {
        // given

        // when
        ResponseEntity<SpotifySearchResponse> response = oacController.searchSpotify("Eminem", "album,artist,track");

        // then
    }
}
