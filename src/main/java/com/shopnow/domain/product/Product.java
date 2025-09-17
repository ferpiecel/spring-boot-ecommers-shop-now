package com.shopnow.domain.product;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private final ProductId id;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;

    private Product(ProductId id, String name, String description, BigDecimal price, int stockQuantity) {
        this.id = id; this.name = Objects.requireNonNull(name); this.description = description; this.price = Objects.requireNonNull(price); this.stockQuantity = stockQuantity;
    }
    public static Product createNew(String name, String description, BigDecimal price, int stockQuantity) { return new Product(ProductId.newId(), name, description, price, stockQuantity); }
    public static Product restore(ProductId id, String name, String description, BigDecimal price, int stockQuantity) { return new Product(id, name, description, price, stockQuantity); }
    public void rename(String n) { this.name = Objects.requireNonNull(n); }
    public void changePrice(BigDecimal p) { this.price = Objects.requireNonNull(p); }
    public void adjustStock(int q) { this.stockQuantity = q; }
    public ProductId getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
}
