package com.orange.exception;

public class EntityAlreadyExist extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExist(String message) {
        super(message);
    }
}
