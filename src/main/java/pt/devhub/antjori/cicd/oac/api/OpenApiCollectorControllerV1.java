package pt.devhub.antjori.cicd.oac.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pt.devhub.antjori.cicd.oac.spotify.model.response.SpotifySearchResponse;
import pt.devhub.antjori.cicd.oac.spotify.service.SpotifyService;

/**
 * REST controller for the Open API Collector.
 */
@RestController
@RequestMapping(value = "/oac/v1")
public class OpenApiCollectorControllerV1 {

    @Autowired
    private SpotifyService spotifyService;

    /**
     * Retrieves a simple string saying Spotify.
     *
     * @return a {@link String} object saying Spotify
     */
    @ApiOperation(value = "Retrive a simple string saying Spotify")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully called Spotify's API", response = SpotifySearchResponse.class) })
    @GetMapping(value = "/api/spotify", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SpotifySearchResponse> searchSpotify(
            @RequestParam(value = "q", required = true) final String query,
            @RequestParam(required = true) final String type) {
        SpotifySearchResponse searchResponse = spotifyService.search(query, type);

        return new ResponseEntity<>(searchResponse, HttpStatus.OK);
    }
}
