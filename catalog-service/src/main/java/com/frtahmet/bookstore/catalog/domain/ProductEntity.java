package com.frtahmet.bookstore.catalog.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq")
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Product code is required")
    private String code;

    @Column(nullable = false)
    @NotEmpty(message = "Product name is required")
    private String name;

    private String description;

    private String imageUrl;

    @NotNull(message = "Product price is required") @DecimalMin("0.1")
    @Column(nullable = false)
    private BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
