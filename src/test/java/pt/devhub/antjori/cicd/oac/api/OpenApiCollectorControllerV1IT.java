package pt.devhub.antjori.cicd.oac.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import pt.devhub.antjori.cicd.oac.OpenApiCollectorApplication;
import pt.devhub.antjori.cicd.oac.OpenApiCollectorTestHelper;
import pt.devhub.antjori.cicd.oac.spotify.model.ClientCredentials;
import pt.devhub.antjori.cicd.oac.spotify.model.response.SpotifySearchResponse;
import pt.devhub.antjori.cicd.oac.spotify.util.SpotifyElementType;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link OpenApiCollectorControllerV1} where will be depicted
 * the integration tests.
 */
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = { OpenApiCollectorApplication.class, OpenApiCollectorControllerV1IT.ContextConfiguration.class })
public class OpenApiCollectorControllerV1IT {

    /**
     * Common request mapping URL path.
     */
    private static final String REQUEST_MAPPING = "/oac/v1";

    @Value(value = "${spring.security.user.name}")
    private String user;

    @Value(value = "${spring.security.user.password}")
    private String password;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OpenApiCollectorTestHelper testHelper;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Mock
    private ResponseEntity<ClientCredentials> clientCredentialsResponseEntity;

    @Mock
    private ClientCredentials clientCredentials;

    @Mock
    private ResponseEntity<SpotifySearchResponse> spotifySearchResponseEntity;

    @BeforeEach
    public void setup() {
        when(clientCredentials.getAccessToken())
                .thenReturn("BQBkcbGl9poui2Cp-_2S3P_n2gA1-ImfTqyI2SFzc8sKa7q0tKfcqipake2f46C4bZXp40kZnSus-Ajey4E");
    }

    /**
     * Specific ITs configuration in order to mock {@link RestTemplateBuilder} and
     * {@link RestTemplate} interactions.
     */
    @Configuration
    static class ContextConfiguration {
        @Bean
        public RestTemplateBuilder restTemplateBuilder() {

            RestTemplateBuilder rtb = Mockito.mock(RestTemplateBuilder.class);
            RestTemplate restTemplate = Mockito.mock(RestTemplate.class);

            when(rtb.build()).thenReturn(restTemplate);

            return rtb;
        }
    }

    private RequestPostProcessor httpBasic() {
        return SecurityMockMvcRequestPostProcessors.httpBasic(this.user, this.password);
    }

    // =======
    // SPOTIFY
    // =======

    @Test
    public void testSearchSpotifyAlbums() throws Exception {
        // given
        MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/api/spotify").with(httpBasic());
        get.param("q", "Eminem");
        get.param("type", SpotifyElementType.ALBUM.getType());

        SpotifySearchResponse spotifySearchResponse = new SpotifySearchResponse();
        spotifySearchResponse.setAlbums(this.testHelper.createSpotifyAlbums());

        // Mocking call to Spotify
        when(testRestTemplate.getRestTemplate().exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(ClientCredentials.class))).thenReturn(clientCredentialsResponseEntity);
        when(clientCredentialsResponseEntity.getBody()).thenReturn(clientCredentials);
        when(testRestTemplate.getRestTemplate().exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(SpotifySearchResponse.class), anyString(), anyString())).thenReturn(spotifySearchResponseEntity);
        when(spotifySearchResponseEntity.getBody()).thenReturn(spotifySearchResponse);

        // when
        ResultActions resultActions = mvc.perform(get).andDo(print());

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
        resultActions.andExpect(jsonPath("$").exists());
        resultActions.andExpect(jsonPath("$.albums").exists());
    }

    @Test
    public void testSearchSpotifyArtists() throws Exception {
        // given
        MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/api/spotify").with(httpBasic());
        get.param("q", "Eminem");
        get.param("type", SpotifyElementType.ARTIST.getType());

        SpotifySearchResponse spotifySearchResponse = new SpotifySearchResponse();
        spotifySearchResponse.setArtists(this.testHelper.createSpotifyArtists());

        // Mocking call to Spotify
        when(testRestTemplate.getRestTemplate().exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(ClientCredentials.class))).thenReturn(clientCredentialsResponseEntity);
        when(clientCredentialsResponseEntity.getBody()).thenReturn(clientCredentials);
        when(testRestTemplate.getRestTemplate().exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(SpotifySearchResponse.class), anyString(), anyString())).thenReturn(spotifySearchResponseEntity);
        when(spotifySearchResponseEntity.getBody()).thenReturn(spotifySearchResponse);

        // when
        ResultActions resultActions = mvc.perform(get).andDo(print());

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));
        resultActions.andExpect(jsonPath("$").exists());
        resultActions.andExpect(jsonPath("$.artists").exists());
    }

    @Test
    public void testSearchSpotifyTracks() throws Exception {
        // given
        MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/api/spotify")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString((user + ":" + password).getBytes()))
                .param("q", "Eminem").param("type", SpotifyElementType.TRACK.getType());

        SpotifySearchResponse spotifySearchResponse = new SpotifySearchResponse();
        spotifySearchResponse.setTracks(this.testHelper.createSpotifyTracks());

        // Mocking call to Spotify
        when(testRestTemplate.getRestTemplate().exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(ClientCredentials.class))).thenReturn(clientCredentialsResponseEntity);
        when(clientCredentialsResponseEntity.getBody()).thenReturn(clientCredentials);
        when(testRestTemplate.getRestTemplate().exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class),
                eq(SpotifySearchResponse.class), anyString(), anyString())).thenReturn(spotifySearchResponseEntity);
        when(spotifySearchResponseEntity.getBody()).thenReturn(spotifySearchResponse);

        // when
        ResultActions resultActions = mvc.perform(get).andDo(print());

        // then
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE));
        resultActions.andExpect(jsonPath("$").exists());
        resultActions.andExpect(jsonPath("$.tracks").exists());
    }

    @Test
    public void testSearchSpotify_WithoutParams() throws Exception {
        // given
        MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/api/spotify").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString((user + ":" + password).getBytes()));

        // when
        ResultActions resultActions = mvc.perform(get).andDo(print());

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    public void testSearchSpotify_WithoutQ() throws Exception {
        // given
        String type = Arrays.asList(SpotifyElementType.values()).stream().map(x -> x.getType())
                .collect(Collectors.joining(","));

        MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/api/spotify").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString((user + ":" + password).getBytes())).param("type", type);

        // when
        ResultActions resultActions = mvc.perform(get).andDo(print());

        // then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    public void testSearchSpotify_WithoutType() throws Exception {
        // given
        MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/api/spotify").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString((user + ":" + password).getBytes())).param("q", "Eminem");

        // when
        ResultActions resultActions = mvc.perform(get).andDo(print());

        // then
        resultActions.andExpect(status().isBadRequest());
    }
}
