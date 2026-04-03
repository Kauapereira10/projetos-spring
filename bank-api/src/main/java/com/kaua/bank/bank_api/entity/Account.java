package com.kaua.bank.bank_api.entity;

import com.kaua.bank.bank_api.enums.AccountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Table(name = "account")
public class Account {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_holder", nullable = false)
    private String nameHolder;

    @Column(name = "balance", columnDefinition = "DECIMAL(10,2) DEFAULT 0.00", nullable = false)
    private double balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type",  nullable = false)
    private AccountType type;

    public void setNameHolder(String nameHolder) throws Exception {
        if(nameHolder == null  || nameHolder.isBlank()) {
            throw new RuntimeException("O nome não pode estar vazio.");
        }
        this.nameHolder = nameHolder;
    }

    public void setBalance(double balance) throws Exception {
        if(balance < 0) {
            throw new RuntimeException("O saldo não pode ser negativo.");
        }
        this.balance = balance;
    }

    public void setType(AccountType type) throws Exception {
        if(type == null) {
            throw new RuntimeException("Só pode ser corrente ou poupança");
        }
        this.type = type;
    }


}
