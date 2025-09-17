package com.shopnow.infrastructure.persistence.product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataProductRepository extends JpaRepository<JpaProductEntity, String> {}
