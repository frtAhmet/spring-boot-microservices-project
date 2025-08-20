package com.frtahmet.bookstore.catalog.web.controllers;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import com.frtahmet.bookstore.catalog.AbstractIT;
import com.frtahmet.bookstore.catalog.domain.ProductResponse;
import io.restassured.http.ContentType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void shouldReturnProducts() {

        given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(25))
                .body("totalPages", is(3))
                .body("pageNumber", is(1))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));
    }

    @Test
    void shouldGetProductByCode() {

        String code = "book-1";

        ProductResponse product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products/{code}", code)
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(ProductResponse.class);

        assertThat(product.code()).isEqualTo(code);
        assertThat(product.name()).isEqualTo("Effective Java");
        assertThat(product.description()).isEqualTo("A comprehensive guide to programming in Java.");
        assertThat(product.price()).isEqualTo(new BigDecimal("45.00"));
    }

    @Test
    void shouldReturnNotFoundWhenProductCodeNotExists() {

        String code = "invalid_product_code";

        given().contentType(ContentType.JSON)
                .when()
                .get("/api/v1/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with code " + code + " not found."));
    }
}
