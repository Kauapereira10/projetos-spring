package com.kaua.booking_api.dto.user;

import com.kaua.booking_api.enums.UserType;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String phone,
        UserType userType,
        LocalDateTime createdAt
) {
}