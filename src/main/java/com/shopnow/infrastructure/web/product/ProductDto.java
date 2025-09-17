package com.shopnow.infrastructure.web.product;

import com.shopnow.domain.product.Product;
import java.math.BigDecimal;

public record ProductDto(String id, String name, String description, BigDecimal price, int stockQuantity) {
    public static ProductDto from(Product p) { return new ProductDto(p.getId().toString(), p.getName(), p.getDescription(), p.getPrice(), p.getStockQuantity()); }
}
