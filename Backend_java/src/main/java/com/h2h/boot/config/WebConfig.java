package com.h2h.boot.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow requests from React frontend only
        registry.addMapping("/**")  // Allows all endpoints to accept CORS
            .allowedOrigins("http://localhost:3000")  // Replace with your React app URL
            .allowedMethods("GET", "POST", "PUT", "DELETE")  // Specify allowed HTTP methods
            .allowedHeaders("*");  // Allow all headers, or specify custom headers
    }
}
