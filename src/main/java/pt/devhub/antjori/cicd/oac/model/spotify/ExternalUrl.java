package pt.devhub.antjori.cicd.oac.model.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * Mapping for Spotify's album item external URL.
 */
@Data
@JsonInclude(value = Include.NON_NULL)
public class ExternalUrl {

    /**
     * Spotify's external url.
     */
    private String spotify;
}