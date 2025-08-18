package com.frtahmet.bookstore.catalog.web.controllers;

import com.frtahmet.bookstore.catalog.AbstractIT;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void shouldReturnProducts() {

        given()
                .contentType(ContentType.JSON)
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

}