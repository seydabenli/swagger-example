package com.swagger.example.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
public class Swagger {

  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.swagger.example"))
        .paths(PathSelectors.regex("/.*"))
        .build().apiInfo(apiEnpointInfo());
  }

  private ApiInfo apiEnpointInfo() {
    return new ApiInfoBuilder().title("Swagger Example")
        .description("User API Documentaion")
        .contact(new Contact("Example Swagger", "", ""))
        .license("Apache 2.0")
        .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
        .version("1.12.3")
        .build();
  }

}
