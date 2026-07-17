package com.kaua.booking_api.dto.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServiceResponseDTO(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer durationMinutes,
        LocalDateTime createdAt,
        Long providerId,
        String providerName
) {
}