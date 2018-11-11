package pt.devhub.antjori.cicd.oac.model;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.Data;

/**
 * Class that houses the web APIs URL configuration.
 */
@Data
public class Url {

    /**
     * The type of the request method.
     */
    private RequestMethod type;

    /**
     * The web API url.
     */
    private String url;
}
