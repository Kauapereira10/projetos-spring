package com.kaua.booking_api.exeptions;

public class ResourceNotFoundException extends BusinessException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
