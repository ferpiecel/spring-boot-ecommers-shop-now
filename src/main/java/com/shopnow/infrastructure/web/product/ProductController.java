package com.shopnow.infrastructure.web.product;

import com.shopnow.application.product.CreateProductCommand;
import com.shopnow.application.product.CreateProductService;
import com.shopnow.domain.product.ProductId;
import com.shopnow.domain.product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final CreateProductService createProductService;
    private final ProductRepository repository;
    public ProductController(CreateProductService createProductService, ProductRepository repository) { this.createProductService = createProductService; this.repository = repository; }

    @PostMapping
    public ResponseEntity<ProductDto> create(@Validated @RequestBody CreateProductRequest body) {
        ProductId id = createProductService.handle(new CreateProductCommand(body.name(), body.description(), body.price(), body.stockQuantity()));
        return repository.findById(id)
                .map(ProductDto::from)
                .map(dto -> ResponseEntity.created(URI.create("/api/products/" + id)).body(dto))
                .orElseGet(() -> ResponseEntity.internalServerError().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> get(@PathVariable String id) {
        return repository.findById(ProductId.fromString(id))
                .map(ProductDto::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ProductDto> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return repository.findAll(page, size).stream().map(ProductDto::from).collect(Collectors.toList());
    }
}
