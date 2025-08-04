package com.prediction_crime;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jdk.javadoc.doclet.Doclet;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@EnableSwagger2
public class SwaggerConfiguration {

//    @Bean
//    public Doclet api() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .groupName("REST API V1")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.prediction_crime"))
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(new ApiInfoBuilder()
//                        .title("Prediction de crime REST API")
//                        .description("Documentation de l’API de prédiction de crime")
//                        .version("1.0")
//                        .build());
//    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Prediction de crime REST API")
                        .version("1.0")
                        .description("Documentation de l’API de prédiction de crime"));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/**")
                .build();
    }
}
