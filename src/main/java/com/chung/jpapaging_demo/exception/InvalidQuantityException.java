package com.chung.jpapaging_demo.exception;

import lombok.Data;

@Data
public class InvalidQuantityException extends RuntimeException{
    private String message;
    public InvalidQuantityException(String message) {
        super();
        this.message = message;
    }
}
