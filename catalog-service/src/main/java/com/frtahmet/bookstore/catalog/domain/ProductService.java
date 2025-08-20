package com.frtahmet.bookstore.catalog.domain;

import com.frtahmet.bookstore.catalog.ApplicationProperties;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ApplicationProperties applicationProperties;

    ProductService(ProductRepository productRepository, ApplicationProperties applicationProperties) {
        this.productRepository = productRepository;
        this.applicationProperties = applicationProperties;
    }

    public PagedResult<ProductResponse> getAllProducts(int pageNo) {
        Sort sort = Sort.by("name").ascending();
        pageNo = pageNo <= 1 ? 0 : pageNo - 1; // Convert to zero-based index for PageRequest
        Pageable pageable =
                PageRequest.of(pageNo, applicationProperties.pageSize(), sort); // Assuming a page size of 10
        Page<ProductResponse> productPage = productRepository.findAll(pageable).map(ProductMapper::toProductResponse);

        return new PagedResult<>(
                productPage.getContent(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                productPage.getNumber() + 1, // Convert to one-based index for the result
                productPage.isFirst(),
                productPage.isLast(),
                productPage.hasNext(),
                productPage.hasPrevious());
    }

    public Optional<ProductResponse> getProductByCode(String code) {
        return productRepository.findByCode(code).map(ProductMapper::toProductResponse);
    }
}
