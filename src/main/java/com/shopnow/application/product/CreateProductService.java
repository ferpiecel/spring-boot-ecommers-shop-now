package com.shopnow.application.product;

import com.shopnow.domain.product.Product;
import com.shopnow.domain.product.ProductId;
import com.shopnow.domain.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateProductService {
    private final ProductRepository repository;
    public CreateProductService(ProductRepository repository) { this.repository = repository; }
    
    @Transactional
    public ProductId handle(CreateProductCommand cmd) {
        Product p = Product.createNew(cmd.name(), cmd.description(), cmd.price(), cmd.stockQuantity());
        repository.save(p);
        return p.getId();
    }
}
