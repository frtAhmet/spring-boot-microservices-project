package com.frtahmet.bookstore.catalog.web.controllers;

import com.frtahmet.bookstore.catalog.domain.PagedResult;
import com.frtahmet.bookstore.catalog.domain.ProductNotFoundException;
import com.frtahmet.bookstore.catalog.domain.ProductResponse;
import com.frtahmet.bookstore.catalog.domain.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    PagedResult<ProductResponse> getAllProducts(@RequestParam(name = "page", defaultValue = "1") int pageNo) {
        // This method should return a list of products.
        // For now, we will return an empty list.
        return productService.getAllProducts(pageNo);
    }

    @GetMapping("/{code}")
    ResponseEntity<ProductResponse> getProductByCode(@PathVariable String code) {

        return productService
                .getProductByCode(code)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> ProductNotFoundException.withCode(code));
    }
}
