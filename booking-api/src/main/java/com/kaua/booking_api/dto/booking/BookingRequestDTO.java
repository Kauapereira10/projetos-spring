package com.kaua.booking_api.dto.booking;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BookingRequestDTO(

        @NotNull(message = "Id do serviço é obrigatório.")
        Long serviceId,

        @NotNull(message = "Id do cliente é obrigatório.")
        Long clientId,

        String notes,

        @NotNull(message = "Horário de início é obrigatório")
        @Future(message = "O horário de início deve ser no futuro")
        LocalDateTime startTime

) {
}