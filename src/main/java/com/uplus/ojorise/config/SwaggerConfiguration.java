package com.uplus.ojorise.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import io.jsonwebtoken.lang.Arrays;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
//import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;

// Swagger-UI 확인
// http://localhost:8080/ojoRise/swagger-ui/swagger-ui/index.html

//@OpenAPIDefinition(
//	    info = @Info(
//	        title = "OjoRise SpringTest API 명세서",
//	        description = "<h3>SpringTest API Reference for Developers</h3>Swagger를 이용한 SpringTest API<br><img src=\"/assets/img/ssafy_logo.png\" width=\"150\">",
//	        version = "v1"
//	    )
//	)
//@SecuritySchemes  ({
//        @SecurityScheme(
//                name = "AccessToken",
//                type = SecuritySchemeType.HTTP,
//                bearerFormat = "JWT",
//                scheme = "bearer"
//        ),
//        @SecurityScheme(
//                name = "RefreshToken",
//                type = SecuritySchemeType.APIKEY, // HTTP가 아닌 API Key 방식 사용
//                in = SecuritySchemeIn.HEADER,
//                paramName = "Refresh-Token"
//        )
//})

@Configuration
public class SwaggerConfiguration {
    private Logger logger = LoggerFactory.getLogger(getClass());
    public SwaggerConfiguration() {
        logger.debug("SwaggerConfiguration.................");
    }
    @Bean
    public OpenAPI openOjoRiseAPI() {
        logger.debug("openOjoRiseAPI.............");

        Info info = new Info().title("OjoRise SpringTest API 명세서").description(
//                        "<h3>SpringTest API Reference for Developers</h3>Swagger를 이용한 SpringTest API<br><img src=\"/eureka/img/eureka_logo.png\" width=\"50\">")
                        "<h3>SpringTest API Reference for Developers</h3>Swagger를 이용한 SpringTest API")
                .version("v1");

        SecurityScheme bearerAuth = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("BearerAuth", bearerAuth))
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .info(info);
    }

//    @Bean
//    public GroupedOpenApi memberApi() {
//        return GroupedOpenApi.builder().group("eureka-member").pathsToMatch("/member/**").build();
//    }
//
//    @Bean
//    public GroupedOpenApi bookApi() {
//        return GroupedOpenApi.builder().group("eureka-book").pathsToMatch("/book/**").build();
//    }
//    @Bean
//    public GroupedOpenApi testApi() {
//        return GroupedOpenApi.builder().group("eureka-test").pathsToMatch("/test/**").build();
//    }

}