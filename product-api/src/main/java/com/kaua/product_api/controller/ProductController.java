package com.kaua.product_api.controller;

import com.kaua.product_api.dto.ProductRequestDTO;
import com.kaua.product_api.dto.ProductResponseDTO;
import com.kaua.product_api.entity.Product;
import com.kaua.product_api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping(path = "/products")
    public ResponseEntity<Page<ProductResponseDTO>> findAllPage(@RequestParam(defaultValue = "") String name, Pageable pageable) {
        return new ResponseEntity<>(service.findAll(name, pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/products/{id}")
    public ResponseEntity<ProductResponseDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/products")
    public ResponseEntity<ProductResponseDTO> created(@Valid @RequestBody ProductRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.createProduct(requestDTO));
    }

    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/products/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,@Valid @RequestBody ProductRequestDTO requestDTO) {
        return new ResponseEntity<>(service.updateProduct(id, requestDTO), HttpStatus.OK);
    }

}
