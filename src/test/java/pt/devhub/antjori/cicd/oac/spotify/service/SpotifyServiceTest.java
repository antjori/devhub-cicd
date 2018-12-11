package pt.devhub.antjori.cicd.oac.spotify.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import pt.devhub.antjori.cicd.oac.spotify.config.SpotifyConfig;
import pt.devhub.antjori.cicd.oac.spotify.model.ClientCredentials;
import pt.devhub.antjori.cicd.oac.spotify.model.response.SpotifySearchResponse;
import pt.devhub.antjori.cicd.oac.spotify.util.Credentials;
import pt.devhub.antjori.cicd.oac.spotify.util.Url;

/**
 * Test class for {@link SpotifyService} where will be depicted the classe's
 * unit tests.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ObjectUtils.class)
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

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PowerMockito.mockStatic(ObjectUtils.class);

        // token URL
        tokenUrl = new Url();
        tokenUrl.setType(HttpMethod.POST);
        tokenUrl.setUrl("https://accounts.spotify.com/api/token");

        // search URL
        searchUrl = new Url();
        searchUrl.setType(HttpMethod.GET);
        searchUrl.setUrl("https://api.spotify.com/v1/search?q={query}&type={type}");

        when(clientCredentials.getAccessToken())
                .thenReturn("BQBkcbGl9poui2Cp-_2S3P_n2gA1-ImfTqyI2SFzc8sKa7q0tKfcqipake2f46C4bZXp40kZnSus-Ajey4E");
    }

    @Test
    public void testSearch_withoutCredentials() {
        // given
        when(ObjectUtils.isEmpty(any())).thenReturn(Boolean.TRUE);
        when(restTemplate.getInterceptors()).thenReturn(new ArrayList<ClientHttpRequestInterceptor>());
        when(spotifyConfig.getCredentials()).thenReturn(credentials);
        when(credentials.getClientId()).thenReturn("clientId");
        when(credentials.getClientSecret()).thenReturn("clientSecret");
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
        PowerMockito.verifyStatic(ObjectUtils.class, times(1));
        ObjectUtils.isEmpty(anyBoolean());

        assertNotNull(response);

        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(ClientCredentials.class));
        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(SpotifySearchResponse.class), anyString(), anyString());
    }

    @Test
    public void testSearch_withCredentials() {
        // given
        when(ObjectUtils.isEmpty(any())).thenReturn(Boolean.FALSE);
        when(spotifyConfig.getSearchUrl()).thenReturn(searchUrl);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(SpotifySearchResponse.class), anyString(), anyString())).thenReturn(spotifySearchResponseEntity);
        when(spotifySearchResponseEntity.getBody()).thenReturn(spotifySearchResponse);

        // when
        SpotifySearchResponse response = spotifyService.search("Eminem", "album,artist,track");

        // then
        PowerMockito.verifyStatic(ObjectUtils.class, times(1));
        ObjectUtils.isEmpty(anyBoolean());

        assertNotNull(response);

        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(SpotifySearchResponse.class), anyString(), anyString());
    }
}
