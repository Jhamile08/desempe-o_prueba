package com.riwi.filtro_spring_boot.utils.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}

