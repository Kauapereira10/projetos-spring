package com.kaua.booking_api.dto.service;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ServiceRequestDTO(

        @NotBlank(message = "Nome é obrigatório.")
        @Size(min = 5, max = 100, message = "Nome do serviço deve possuir entre 5 a 100 caracteres.")
        String name,

        @NotBlank(message = "Descrição é obrigatório.")
        @Size(min = 5, max = 100, message = "A descrição do serviço deve possuir entre 5 a 100 caracteres.")
        String description,

        @NotNull(message = "Preço é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero.")
        BigDecimal price,

        @NotNull(message = "Duração do serviço em minutos é obrigatório.")
        @Positive(message = "Duração deve ser maior que zero")
        Integer durationMinutes,

        @NotNull(message = "Id do prestador é obrigatório")
        Long providerId

) {
}