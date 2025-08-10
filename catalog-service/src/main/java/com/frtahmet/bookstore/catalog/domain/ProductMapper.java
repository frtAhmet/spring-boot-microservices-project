package com.frtahmet.bookstore.catalog.domain;

class ProductMapper {

    static ProductResponse toProductResponse(ProductEntity productEntity) {
        return new ProductResponse(
                productEntity.getCode(),
                productEntity.getName(),
                productEntity.getDescription(),
                productEntity.getImageUrl(),
                productEntity.getPrice()
        );
    }
}
