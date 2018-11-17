package pt.devhub.antjori.cicd.oac.model.spotify;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;

/**
 * Mapping for Spotify's album item external URL.
 */
@Data
@ToString
@JsonInclude(value = Include.NON_NULL)
public class ExternalUrls {

    /**
     * Spotify's external URL.
     */
    private String spotify;
}