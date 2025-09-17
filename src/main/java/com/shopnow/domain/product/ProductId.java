package com.shopnow.domain.product;

import java.util.UUID;

public record ProductId(UUID value) {
    public ProductId { if (value == null) throw new IllegalArgumentException("ProductId null"); }
    public static ProductId newId() { return new ProductId(UUID.randomUUID()); }
    public String toString() { return value.toString(); }
    public static ProductId fromString(String id) { return new ProductId(UUID.fromString(id)); }
}
