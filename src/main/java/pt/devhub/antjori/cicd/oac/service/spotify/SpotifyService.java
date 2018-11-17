package pt.devhub.antjori.cicd.oac.service.spotify;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.RequiredArgsConstructor;
import pt.devhub.antjori.cicd.oac.config.SpotifyConfig;
import pt.devhub.antjori.cicd.oac.model.spotify.ClientCredentials;
import pt.devhub.antjori.cicd.oac.model.spotify.SpotifySearchResponse;
import pt.devhub.antjori.cicd.oac.util.WebAPIConstants;

/**
 * The service that allows the communication with Spotify Web API.
 */
@Service
@RequiredArgsConstructor
public class SpotifyService {

    // The client credentials.
    private ClientCredentials clientCredentials;

    // The Spotify's Spring Boot configuration.
    @Autowired
    private SpotifyConfig spotifyConfig;

    public SpotifySearchResponse search(final String query, final String type) {

        if (ObjectUtils.isEmpty(clientCredentials)) {
            // Request authorization
            authorize();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.clientCredentials.getAccessToken());

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<SpotifySearchResponse> response = restTemplate.exchange(
                this.spotifyConfig.getSearchUrl().getUrl(), this.spotifyConfig.getSearchUrl().getType(),
                new HttpEntity<>(headers), SpotifySearchResponse.class, query, type);

        return response.getBody();
    }

    private void authorize() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(WebAPIConstants.GRANT_TYPE, WebAPIConstants.CLIENT_CREDENTIALS);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors()
                .add(new BasicAuthorizationInterceptor(
                        new String(Base64.decodeBase64(this.spotifyConfig.getCredentials().getClientId())),
                        new String(Base64.decodeBase64(this.spotifyConfig.getCredentials().getClientSecret()))));

        ResponseEntity<ClientCredentials> response = restTemplate.exchange(this.spotifyConfig.getTokenUrl().getUrl(),
                this.spotifyConfig.getTokenUrl().getType(), request, ClientCredentials.class);

        this.clientCredentials = response.getBody();
    }
}
