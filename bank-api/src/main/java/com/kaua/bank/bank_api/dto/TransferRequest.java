package com.kaua.bank.bank_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {

    private Long idOrigem;
    private Long idDestino;
    private double value;
}
