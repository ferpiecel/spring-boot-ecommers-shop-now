package com.shopnow.infrastructure.persistence.product;

import com.shopnow.domain.product.Product;
import com.shopnow.domain.product.ProductId;
import com.shopnow.domain.product.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {
    private final SpringDataProductRepository delegate;
    public ProductRepositoryAdapter(SpringDataProductRepository delegate) { this.delegate = delegate; }
    @Override public Product save(Product product) { delegate.save(JpaProductEntity.fromDomain(product)); return product; }
    @Override public Optional<Product> findById(ProductId id) { return delegate.findById(id.toString()).map(JpaProductEntity::toDomain); }
    @Override public List<Product> findAll(int page, int size) { return delegate.findAll(PageRequest.of(page, size)).map(JpaProductEntity::toDomain).getContent(); }
}
