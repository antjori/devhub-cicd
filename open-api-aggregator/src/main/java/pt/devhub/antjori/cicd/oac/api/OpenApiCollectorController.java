package pt.devhub.antjori.cicd.oac.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for the Open API Collector.
 */
@RestController
public class OpenApiCollectorController {

    @RequestMapping(value = "/api/spotify")
    public String getSpotify() {
        return "spotify";
    }
}
