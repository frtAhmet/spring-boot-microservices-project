package com.frtahmet.bookstore.catalog.web.exception;

import com.frtahmet.bookstore.catalog.domain.ProductNotFoundException;
import java.net.URI;
import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final URI NOT_FOUND_TYPE = URI.create("https://bookstore.com/errors/not-found");
    private static final URI INTERNAL_SERVER_ERROR_FOUND_TYPE =
            URI.create("https://bookstore.com/errors/internal-server-error");
    private static final String SERVICE_NAME = "catalog-service";
    //    private static final URI BAD_REQUEST_TYPE = URI.create("https://bookstore.com/errors/bad-request");
    //    private static final URI CONFLICT_TYPE = URI.create("https://bookstore.com/errors/conflict");
    //    private static final URI UNAUTHORIZED_TYPE = URI.create("https://bookstore.com/errors/unauthorized");

    @ExceptionHandler(Exception.class)
    ProblemDetail handleUnhandledException(Exception e) {
        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setType(INTERNAL_SERVER_ERROR_FOUND_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Generic");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    ProblemDetail handleProductNotFoundException(ProductNotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Product Not Found");
        problemDetail.setType(NOT_FOUND_TYPE);
        problemDetail.setProperty("service", SERVICE_NAME);
        problemDetail.setProperty("error_category", "Product");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }
}
