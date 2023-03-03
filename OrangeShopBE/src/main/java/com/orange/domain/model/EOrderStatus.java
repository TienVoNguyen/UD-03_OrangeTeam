package com.orange.domain.model;

public enum EOrderStatus {
    COMPLETED(1),
    CANCEL(2),
    WAIT_FOR_PAY(3),
    DELIVERY_IN_PROGRESS(4),
    WAIT_FOR_CONFIRMATION(5),
    CONFIRMED(6);

    private final int id;

    EOrderStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
