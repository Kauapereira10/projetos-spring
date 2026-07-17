package com.kaua.booking_api.dto.booking;

import com.kaua.booking_api.enums.BookingStatus;

import java.time.LocalDateTime;

public record BookingResponseDTO(
        Long id,
        String clientName,
        String providerName,
        String serviceName,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime startTime,
        LocalDateTime endTime,
        BookingStatus status
) {
}