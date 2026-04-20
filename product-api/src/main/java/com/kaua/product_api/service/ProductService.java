package com.kaua.product_api.service;

import com.kaua.product_api.dto.ProductRequestDTO;
import com.kaua.product_api.dto.ProductResponseDTO;
import com.kaua.product_api.entity.Product;
import com.kaua.product_api.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Page<ProductResponseDTO> findAll(String name, Pageable pageable) {
        if(name.isEmpty()) {
            Page<Product> products = repository.findAll(pageable);
            return products.map(product -> new ProductResponseDTO(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getCreatedAt()
            ));
        } else {
            Page<Product> products = repository.findByNameIgnoreCaseContaining(name, pageable);
            return products.map(product -> new ProductResponseDTO(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getCreatedAt()
            ));
        }

    }

    public ProductResponseDTO findById(Long id) {
        Product product = verifyIfProductsExists(id);
        ProductResponseDTO responseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getCreatedAt()
        );
        return responseDTO;
    }

    public ProductResponseDTO createProduct(ProductRequestDTO requestDTO) {
        Product product = new Product();
        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setCategory(requestDTO.getCategory());
        Product savedProduct = repository.save(product);
        return new ProductResponseDTO(savedProduct.getId(),savedProduct.getName(), savedProduct.getPrice(), savedProduct.getCategory(), savedProduct.getCreatedAt());
    }

    public ProductResponseDTO updateProduct(Long id, ProductRequestDTO requestDTO) {
        Product product = verifyIfProductsExists(id);

        product.setName(requestDTO.getName());
        product.setPrice(requestDTO.getPrice());
        product.setCategory(requestDTO.getCategory());
        repository.save(product);

        ProductResponseDTO responseDTO = new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getCreatedAt()
        );

        return responseDTO;
    }

    public void deleteProduct(Long id) {
        Product product = verifyIfProductsExists(id);

        repository.delete(product);
    }

    private Product verifyIfProductsExists(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
    }

}
