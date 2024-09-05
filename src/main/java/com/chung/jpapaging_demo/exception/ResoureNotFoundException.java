package com.chung.jpapaging_demo.exception;

import lombok.Data;

@Data
public class ResoureNotFoundException extends RuntimeException{
    private String message;
    public ResoureNotFoundException(String message) {
        super();
        this.message = message;
    }
}
