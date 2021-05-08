package com.swagger.example.config;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.spring.web.DescriptionResolver;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import java.util.Optional;

import org.springframework.core.annotation.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
public class SwaggerApiReader implements OperationBuilderPlugin {

  private final DescriptionResolver descriptions;

  public SwaggerApiReader(DescriptionResolver descriptions) {
    this.descriptions = descriptions;
  }

  @Override
  public void apply(final OperationContext context) {
    System.out.println(context.findAnnotation(PreAuthorize.class));
    Optional.ofNullable(context.findAnnotation(PreAuthorize.class)
        .or(context.findControllerAnnotation(PreAuthorize.class))
        .orNull())
        .ifPresent(preAuth -> context.operationBuilder()
            .notes("**Security @PreAuthorize expression:** `" + descriptions.resolve(preAuth.value()) + "`"));
  }

  @Override
  public boolean supports(final DocumentationType delimiter) {
    return SwaggerPluginSupport.pluginDoesApply(delimiter);
  }

}
