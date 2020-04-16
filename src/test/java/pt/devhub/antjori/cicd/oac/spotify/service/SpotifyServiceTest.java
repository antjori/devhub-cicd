package pt.devhub.antjori.cicd.oac.spotify.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import pt.devhub.antjori.cicd.oac.spotify.config.SpotifyConfig;
import pt.devhub.antjori.cicd.oac.spotify.model.ClientCredentials;
import pt.devhub.antjori.cicd.oac.spotify.model.response.SpotifySearchResponse;
import pt.devhub.antjori.cicd.oac.spotify.util.Credentials;
import pt.devhub.antjori.cicd.oac.spotify.util.Url;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for {@link SpotifyService} where will be depicted the class
 * unit tests.
 */
@ExtendWith(MockitoExtension.class)
public class SpotifyServiceTest {

    @InjectMocks
    private SpotifyService spotifyService;

    @Mock
    private RestTemplateBuilder restTemplateBuilder;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private SpotifyConfig spotifyConfig;

    @Mock
    private Credentials credentials;

    @Mock
    private ResponseEntity<ClientCredentials> clientCredentialsResponseEntity;

    @Mock
    private ClientCredentials clientCredentials;

    @Mock
    private ResponseEntity<SpotifySearchResponse> spotifySearchResponseEntity;

    @Mock
    private SpotifySearchResponse spotifySearchResponse;

    private Url tokenUrl;

    private Url searchUrl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

        // token URL
        tokenUrl = new Url();
        tokenUrl.setType(HttpMethod.POST);
        tokenUrl.setUrl("https://accounts.spotify.com/api/token");

        // search URL
        searchUrl = new Url();
        searchUrl.setType(HttpMethod.GET);
        searchUrl.setUrl("https://api.spotify.com/v1/search?q={query}&type={type}");
    }

    @Test
    public void testSearch_withoutCredentials() {
        // given
        ReflectionTestUtils.setField( spotifyService, "clientCredentials", null);

        when(restTemplate.getInterceptors()).thenReturn(new ArrayList<>());
        when(spotifyConfig.getCredentials()).thenReturn(credentials);
        when(credentials.getClientId()).thenReturn("Y2xpZW50SWQ=");
        when(credentials.getClientSecret()).thenReturn("Y2xpZW50U2VjcmV0");
        when(spotifyConfig.getTokenUrl()).thenReturn(tokenUrl);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(ClientCredentials.class))).thenReturn(clientCredentialsResponseEntity);
        when(clientCredentialsResponseEntity.getBody()).thenReturn(clientCredentials);
        when(spotifyConfig.getSearchUrl()).thenReturn(searchUrl);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(SpotifySearchResponse.class), anyString(), anyString())).thenReturn(spotifySearchResponseEntity);
        when(spotifySearchResponseEntity.getBody()).thenReturn(spotifySearchResponse);

        // when
        SpotifySearchResponse response = spotifyService.search("Eminem", "album,artist,track");

        // then
        assertNotNull(response);

        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(ClientCredentials.class));
        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(SpotifySearchResponse.class), anyString(), anyString());
    }

    @Test
    public void testSearch_withCredentials() {
        // given
        ReflectionTestUtils.setField( spotifyService, "clientCredentials", clientCredentials);

        when(clientCredentials.getAccessToken())
                .thenReturn("BQBkcbGl9poui2Cp-_2S3P_n2gA1-ImfTqyI2SFzc8sKa7q0tKfcqipake2f46C4bZXp40kZnSus-Ajey4E");
        when(spotifyConfig.getSearchUrl()).thenReturn(searchUrl);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(SpotifySearchResponse.class), anyString(), anyString())).thenReturn(spotifySearchResponseEntity);
        when(spotifySearchResponseEntity.getBody()).thenReturn(spotifySearchResponse);

        // when
        SpotifySearchResponse response = spotifyService.search("Eminem", "album,artist,track");

        // then
        assertNotNull(response);

        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(SpotifySearchResponse.class), anyString(), anyString());
    }
}
