package com.kaua.product_api.dto;

import com.kaua.product_api.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private Double price;
    private Category category;
    private LocalDateTime createdAt;

}
