package com.shopnow.infrastructure.web.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CreateProductRequest(@NotBlank String name, String description, @NotNull @Min(0) BigDecimal price, @Min(0) int stockQuantity) {}
