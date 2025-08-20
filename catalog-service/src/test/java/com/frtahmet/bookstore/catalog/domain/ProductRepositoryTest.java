package com.frtahmet.bookstore.catalog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest(
        properties = {
            "spring.test.database.replace=none",
            "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        })
// @Import(TestContainersConfiguration.class)
@Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> productResponses = productRepository.findAll();
        assertThat(productResponses).hasSize(25);
    }

    @Test
    void shouldGetProductByCode() {
        String code = "book-1";
        ProductEntity productEntity = productRepository.findByCode(code).orElseThrow();
        assertThat(productEntity.getCode()).isEqualTo(code);
        assertThat(productEntity.getName()).isEqualTo("Effective Java");
        assertThat(productEntity.getDescription()).isEqualTo("A comprehensive guide to programming in Java.");
        assertThat(productEntity.getPrice()).isEqualTo(new BigDecimal("45.00"));
    }

    @Test
    void shouldReturnEmptyWhenProductCodeNotExists() {
        String code = "invalid_product_code";
        assertThat(productRepository.findByCode(code)).isEmpty();
    }
}
