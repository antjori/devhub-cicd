package pt.devhub.antjori.cicd.oac;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * The bootstrap (base) application that will trigger Spring Boot.
 */
@SpringBootApplication(scanBasePackages = "pt.devhub.antjori.cicd.oac")
public class OpenApiCollectorApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(OpenApiCollectorApplication.class).run(args);
    }
}