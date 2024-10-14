package com.campusconnect.CampusConnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfig(){
        return new WebMvcConfigurer(){
            public void addCorsMappings(CorsRegistry registry){
               registry.addMapping("/**")
                       .allowedOrigins("https://campusconnect-0o3k.onrender.com")
                       .allowedMethods(HttpMethod.GET.name(),
                                        HttpMethod.POST.name(),
                                        HttpMethod.DELETE.name(),
                                        HttpMethod.PUT.name())
                       .allowedHeaders(HttpHeaders.CONTENT_TYPE,
                                       HttpHeaders.AUTHORIZATION)
                       .allowCredentials(true)
                       .exposedHeaders(HttpHeaders.AUTHORIZATION);
            }

        };
    }

}
