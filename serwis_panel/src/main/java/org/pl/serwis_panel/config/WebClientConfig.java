package org.pl.serwis_panel.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${token.service.url}")
    String tokenServiceUrl;

    @Value("${pilot.weather.service.url}")
    String pilotWeatherServiceUrl;

     @Bean
     public WebClient webClientTokenServiceURL() {
         return WebClient.builder()
                 .baseUrl(tokenServiceUrl)
                 .build();
     }

     @Bean
     public WebClient webClientPilotServiceWeatherURL() {
         return WebClient.builder()
                 .baseUrl(pilotWeatherServiceUrl)
                 .build();
     }
}
