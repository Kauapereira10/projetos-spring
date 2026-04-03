package com.kaua.bank.bank_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferRequest {

    private Long originId;
    private Long destinationId;
    private double value;
}
