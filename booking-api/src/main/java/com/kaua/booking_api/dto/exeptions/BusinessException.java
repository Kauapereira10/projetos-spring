package com.kaua.booking_api.dto.exeptions;

public class BusinessException extends Exception{
    private String message;

    public BusinessException(String message) {
        this.message = message;
    }

}
