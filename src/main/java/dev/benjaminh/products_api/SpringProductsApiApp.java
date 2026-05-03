package dev.benjaminh.products_api;

import dev.benjaminh.products_api.config.CorsProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CorsProperties.class)
public class SpringProductsApiApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringProductsApiApp.class, args);
	}
}
