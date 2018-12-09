package pt.devhub.antjori.cicd.oac.spotify.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import pt.devhub.antjori.cicd.oac.spotify.model.ClientCredentials;

/**
 * Test class for {@link SpotifyService} where will be depicted the classe's
 * unit tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class SpotifyServiceTest {

    @InjectMocks
    private SpotifyService spotifyService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ClientCredentials clientCredentials;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearch_withoutCredentials() {
        // given

        // when

        // then
    }

    @Test
    public void testSearch_withCredentials() {
        // given

        // when

        // then
    }
}
