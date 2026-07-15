package com.kaua.booking_api.entity;

import com.kaua.booking_api.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 220, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 13)
    private String phone;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Executa AUTOMATICAMENTE antes do INSERT (quando a entidade é salva pela primeira vez).
    // Preenche createdAt e updatedAt sem precisar fazer isso manualmente no service.
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Executa AUTOMATICAMENTE antes do UPDATE (quando uma entidade já existente é alterada).
    // Só atualiza updatedAt — createdAt nunca é tocado depois de criado.
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
