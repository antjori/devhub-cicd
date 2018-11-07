package pt.devhub.antjori.cicd.oac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger configuration class.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("pt.devhub.antjori.cicd.oac.api")).paths(PathSelectors.any())
                .build().apiInfo(this.apiInfo()).useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("OAC API")
                .contact(new Contact("Antonio Ribeiro", "https://www.github.com/antjori", ""))
                .description("A RESTful service that allows to access a collection of open APIs.").version("1.0")
                .license("Apache 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0").build();
    }
}
