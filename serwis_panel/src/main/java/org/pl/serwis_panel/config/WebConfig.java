package org.pl.serwis_panel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Adjust the origin as needed
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}