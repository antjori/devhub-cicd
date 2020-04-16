package pt.devhub.antjori.cicd.oac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * The bootstrap (base) application that will trigger Spring Boot.
 */
@SpringBootApplication
@ComponentScan(basePackages = "pt.devhub.antjori.cicd.oac")
public class OpenApiCollectorApplication {

    public static void main(String[] args) { SpringApplication.run(OpenApiCollectorApplication.class, args); }
}