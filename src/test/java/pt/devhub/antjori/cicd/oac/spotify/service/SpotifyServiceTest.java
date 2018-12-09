package pt.devhub.antjori.cicd.oac.spotify.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.client.ClientHttpRequestInterceptor;
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

        when(clientCredentials.getAccessToken())
                .thenReturn("BQBkcbGl9poui2Cp-_2S3P_n2gA1-ImfTqyI2SFzc8sKa7q0tKfcqipake2f46C4bZXp40kZnSus-Ajey4E");
    }

    @Test
    public void testSearch_withoutCredentials() {
        // given
        when(restTemplate.getInterceptors()).thenReturn(new ArrayList<ClientHttpRequestInterceptor>());

        // when

        // then
//        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
//                ClientCredentials.class);
//        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
//                SpotifySearchResponse.class, anyString(), anyString());
    }

    @Test
    public void testSearch_withCredentials() {
        // given

        // when

        // then
//        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
//                any(SpotifySearchResponse.class), anyString(), anyString());
    }
}
