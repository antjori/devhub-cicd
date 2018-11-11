package pt.devhub.antjori.cicd.oac.service.spotify;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pt.devhub.antjori.cicd.oac.config.SpotifyConfig;

/**
 * The service that allows the communication with Spotify Web API.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class SpotifyService {

    @Autowired
    private SpotifyConfig spotifyConfig;

    public ResponseEntity<String> search() {
        log.info(spotifyConfig.getCredentials().getClientId());
        log.info(spotifyConfig.getCredentials().getClientSecret());
        log.info(spotifyConfig.getTokenUrl().getType().name());
        log.info(spotifyConfig.getTokenUrl().getUrl());
        log.info(spotifyConfig.getSearchUrl().getType().name());
        log.info(spotifyConfig.getSearchUrl().getUrl());

        authenticate();

        return new ResponseEntity<>("Spotify", HttpStatus.OK);
    }

    private void authenticate() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add(new BasicAuthorizationInterceptor(
                new String(Base64.decodeBase64(spotifyConfig.getCredentials().getClientId())),
                new String(Base64.decodeBase64(spotifyConfig.getCredentials().getClientSecret()))));

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("grant_type", "client_credentials");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.exchange(spotifyConfig.getTokenUrl().getUrl(),
                spotifyConfig.getTokenUrl().getType(), request, String.class);

        log.info(response.getBody());
    }
}
