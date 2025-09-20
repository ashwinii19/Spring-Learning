package com.aurionpro.app.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

//This class defines how your API is documented using OpenAPI 3.
//It provides metadata like title, version, description, and contact details.
//It defines where the API can be accessed (e.g., a local server at http://localhost:8080).
//Spring manages this configuration via @Configuration and @Bean.

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI baseOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Student–Course API")
            .version("v1.0.0")
            .description("Simple many-to-many (Student ↔ Course) with DTOs, ModelMapper, paging, and logging.")
            .contact(new Contact().name("Your Team").email("team@example.com")))
        .servers(List.of(
            new Server().url("http://localhost:8080").description("Local")
        ));
  }
}