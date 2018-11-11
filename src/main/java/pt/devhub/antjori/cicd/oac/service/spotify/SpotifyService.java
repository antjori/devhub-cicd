package pt.devhub.antjori.cicd.oac.service.spotify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

        return new ResponseEntity<>("Spotify", HttpStatus.OK);
    }
}
