package com.frtahmet.bookstore.catalog.domain;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
