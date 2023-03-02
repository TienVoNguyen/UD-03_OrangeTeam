package com.orange.exception;

public class NotEnoughStockException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NotEnoughStockException(String message) {
        super(message);
    }
}
