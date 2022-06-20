package com.example.demo;

public enum UpdateType {

    EVENT(0),
    STATUS_UPDATE(1),
    WARNING(2),
    ERROR(3);

    private final int typeNumber;


    private UpdateType(int typeNumber) {
        this.typeNumber = typeNumber;
    }

    public int getTypeNumber() {
        return typeNumber;
    }
}
