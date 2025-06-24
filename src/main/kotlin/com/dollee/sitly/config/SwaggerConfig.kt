package com.dollee.sitly.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(info = Info(title = "Sitly Swagger", description = "Sitly REST API"))
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI? {
        val securityScheme = this.securityScheme
        val securityRequirement =
            this.securityRequireMent

        return OpenAPI()
            .components(Components().addSecuritySchemes("bearerAuth", securityScheme))
            .security(listOf<SecurityRequirement?>(securityRequirement))
            .info(
                io.swagger.v3.oas.models.info.Info()
                    .title("Sitly 시터 플랫폼")
                    .description("시터 플랫폼입니다.")
                    .version("v1.0.0")
            )
    }

    private val securityScheme: SecurityScheme?
        get() = SecurityScheme()
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")
            .`in`(SecurityScheme.In.HEADER)
            .name("Authorization")

    private val securityRequireMent: SecurityRequirement
        get() = SecurityRequirement().addList("bearerAuth")
}
