package com.kaua.product_api.repository;

import com.kaua.product_api.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByNameIgnoreCaseContaining(String name, Pageable pageable);
}
