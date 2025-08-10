package com.frtahmet.bookstore.catalog.domain;

import java.math.BigDecimal;

public record ProductResponse(
        String code,
        String name,
        String description,
        String imageUrl,
        BigDecimal price) {


}
