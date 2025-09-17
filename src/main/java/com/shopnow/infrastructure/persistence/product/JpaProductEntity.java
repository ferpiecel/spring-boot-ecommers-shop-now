package com.shopnow.infrastructure.persistence.product;

import com.shopnow.domain.product.Product;
import com.shopnow.domain.product.ProductId;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "Products")
public class JpaProductEntity {
    @Id
    @Column(name = "product_id", length = 36, nullable = false, updatable = false)
    private String id;

    @Column(name = "product_name", nullable = false)
    private String name;

    @Column(name = "product_description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    protected JpaProductEntity() {}

    private JpaProductEntity(String id, String name, String description, BigDecimal price, Integer stockQuantity) {
        this.id = id; this.name = name; this.description = description; this.price = price; this.stockQuantity = stockQuantity;
    }

    public static JpaProductEntity fromDomain(Product p) {
        return new JpaProductEntity(p.getId().toString(), p.getName(), p.getDescription(), p.getPrice(), p.getStockQuantity());
    }

    public Product toDomain() {
        return Product.restore(new ProductId(UUID.fromString(id)), name, description, price, stockQuantity == null ? 0 : stockQuantity);
    }
}
