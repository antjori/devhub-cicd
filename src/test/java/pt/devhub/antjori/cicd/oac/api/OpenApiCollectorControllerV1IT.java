package pt.devhub.antjori.cicd.oac.api;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.util.Base64Utils;

import pt.devhub.antjori.cicd.oac.OpenApiCollectorApplication;
import pt.devhub.antjori.cicd.oac.model.spotify.response.SpotifySearchResponse;
import pt.devhub.antjori.cicd.oac.service.spotify.SpotifyService;

/**
 * Test class for {@link OpenApiCollectorControllerV1} where will be depicted
 * the integration tests.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = OpenApiCollectorApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
public class OpenApiCollectorControllerV1IT {

    /**
     * Common request mapping URL path.
     */
    private static final String REQUEST_MAPPING = "/oac/v1";

    @Value(value = "${security.user.name}")
    private String user;

    @Value(value = "${security.user.password}")
    private String password;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SpotifyService spotifyService;

    // =======
    // SPOTIFY
    // =======

    @Before
    public void setup() {
        when(spotifyService.search(anyString(), anyString())).thenReturn(new SpotifySearchResponse());
    }

    @Test
    public void testSearchSpotify() throws Exception {
        // given
        MockHttpServletRequestBuilder get = get(REQUEST_MAPPING + "/api/spotify")
                .header(HttpHeaders.AUTHORIZATION,
                        "Basic " + Base64Utils.encodeToString((user + ":" + password).getBytes()))
                .param("q", "Eminem").param("type", "artist");

        // when
        ResultActions resultActions = mvc.perform(get).andDo(print());

        // then
        resultActions.andExpect(status().isOk());
    }
}
