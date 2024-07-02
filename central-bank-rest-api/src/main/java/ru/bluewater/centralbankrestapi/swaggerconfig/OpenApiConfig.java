package ru.bluewater.centralbankrestapi.swaggerconfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "CentralBankXMLEditor",
                description = "Bluewater product",
                version = "demo"
        )
)
@Configuration
public class OpenApiConfig {
        @Bean
        public GroupedOpenApi publicApi() {
                return GroupedOpenApi.builder()
                        .group("public")
                        .pathsToMatch("/api/v1/**")
                        .packagesToScan("ru.bluewater.centralbankrestapi.controller")
                        .build();
        }

}