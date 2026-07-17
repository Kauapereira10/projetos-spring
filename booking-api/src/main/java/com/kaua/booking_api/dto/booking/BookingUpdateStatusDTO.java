package com.kaua.booking_api.dto.booking;

import com.kaua.booking_api.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;

public record BookingUpdateStatusDTO(
        @NotNull(message = "Status é obrigatório (PENDING, CONFIRMED, COMPLETED, CANCELLED_BY_CLIENT, CANCELLED_BY_PROVIDER)")
        BookingStatus status
) {
}