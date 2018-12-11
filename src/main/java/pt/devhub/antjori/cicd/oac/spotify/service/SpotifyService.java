package pt.devhub.antjori.cicd.oac.spotify.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import pt.devhub.antjori.cicd.oac.spotify.config.SpotifyConfig;
import pt.devhub.antjori.cicd.oac.spotify.model.ClientCredentials;
import pt.devhub.antjori.cicd.oac.spotify.model.response.SpotifySearchResponse;
import pt.devhub.antjori.cicd.oac.spotify.util.WebAPIConstants;

/**
 * The service that allows the communication with Spotify Web API.
 */
@Service
public class SpotifyService {

    // The client credentials.
    private ClientCredentials clientCredentials;

    // The Spotify's Spring Boot configuration.
    @Autowired
    private SpotifyConfig spotifyConfig;

    private RestTemplate restTemplate;

    /**
     * Default class constructor.
     * 
     * @param restTemplateBuilder the REST template builder
     */
    public SpotifyService(final RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * Searches Spotify's database taking into account the query and the type passed
     * as arguments.
     * 
     * @param query the search query
     * @param type  one of 'album', 'artist' or 'track
     * @return Spotify's search response taking into account the query and the type
     *         passed as arguments
     */
    public SpotifySearchResponse search(final String query, final String type) {

        if (ObjectUtils.isEmpty(clientCredentials)) {
            // Request authorization token
            this.clientCredentials = authorize();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.clientCredentials.getAccessToken());

        ResponseEntity<SpotifySearchResponse> response = restTemplate.exchange(
                this.spotifyConfig.getSearchUrl().getUrl(), this.spotifyConfig.getSearchUrl().getType(),
                new HttpEntity<>(headers), SpotifySearchResponse.class, query, type);

        return response.getBody();
    }

    /**
     * Executes a POST request on Spotify's API in order to retrieve an
     * authorization token for usage on future requests.
     */
    private ClientCredentials authorize() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(WebAPIConstants.GRANT_TYPE, WebAPIConstants.CLIENT_CREDENTIALS);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        restTemplate.getInterceptors()
                .add(new BasicAuthorizationInterceptor(
                        new String(Base64.decodeBase64(this.spotifyConfig.getCredentials().getClientId())),
                        new String(Base64.decodeBase64(this.spotifyConfig.getCredentials().getClientSecret()))));

        ResponseEntity<ClientCredentials> response = restTemplate.exchange(this.spotifyConfig.getTokenUrl().getUrl(),
                this.spotifyConfig.getTokenUrl().getType(), request, ClientCredentials.class);

        return response.getBody();
    }
}
