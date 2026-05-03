package dev.benjaminh.products_api.config;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "spring.app.cors")
public class CorsProperties {
    private List<String> allowedOriginPatterns = List.of();

    public List<String> getAllowedOriginPatterns() {
        return allowedOriginPatterns;
    }

    public void setAllowedOriginPatterns(List<String> allowedOriginPatterns) {
        this.allowedOriginPatterns = allowedOriginPatterns;
    }

    @PostConstruct
    public void logValues() {
        System.out.println("CORS allowedOriginPatterns = " + allowedOriginPatterns);
    }
}
