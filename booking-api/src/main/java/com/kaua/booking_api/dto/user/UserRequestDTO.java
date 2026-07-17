package com.kaua.booking_api.dto.user;

import com.kaua.booking_api.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @NotBlank(message = "Nome é obrigatório.")
        @Size(min = 5, max = 100, message = "O nome deve possuir entre 5 a 100 caracteres.")
        String name,

        @NotBlank(message = "E-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, max = 20, message = "A senha deve possuir entre 8 e 20 caracteres")
        String password,

        @NotBlank(message = "Telefone é obrigatório")
        String phone,

        @NotNull(message = "O tipo do usuário é obrigatório (CLIENT ou PROVIDER)")
        UserType userType
) {
}