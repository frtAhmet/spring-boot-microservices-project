package com.frtahmet.bookstore.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
// @EnableConfigurationProperties(ApplicationProperties.class) // First option to enable configuration properties
@ConfigurationPropertiesScan // Second option to enable configuration properties, this will scan all classes annotated
// with @ConfigurationProperties
public class CatalogServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }
}
