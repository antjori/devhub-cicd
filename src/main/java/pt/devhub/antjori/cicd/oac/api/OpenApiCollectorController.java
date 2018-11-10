package pt.devhub.antjori.cicd.oac.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pt.devhub.antjori.cicd.oac.service.spotify.SpotifyService;

/**
 * REST controller for the Open API Collector.
 */
@RestController
@RequestMapping(value = "/oac")
public class OpenApiCollectorController {

    @Autowired
    SpotifyService spotifyService;

    /**
     * Retrieves a simple string saying Spotify.
     *
     * @return a {@link String} object saying Spotify
     */
    @ApiOperation(value = "Retrive a simple string saying Spotify")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully called Spotify's API", response = String.class) })
    @RequestMapping(value = "/api/spotify", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> getSpotify() {
        return new ResponseEntity<>("Spotify", HttpStatus.OK);
    }
}
