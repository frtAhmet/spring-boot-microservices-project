package com.frtahmet.bookstore.catalog.domain;

import com.frtahmet.bookstore.catalog.TestContainersConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
        (properties = {
                "spring.test.database.replace=none",
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        }
        )
//@Import(TestContainersConfiguration.class)
@Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void shouldGetAllProducts() {
        List<ProductEntity> productResponses = productRepository.findAll();
        assertThat(productResponses).hasSize(25);
    }
}