package edu.wut.hotels.management.database.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import static java.util.Arrays.stream;

public enum ClientFieldType {
    ADDRESS,
    PESEL;

    //TODO: need testing
    @JsonCreator
    public static ClientFieldType fromValue(String example) {
        return stream(values())
                .filter(v -> v.toString().equals(example.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(example + " does not match any existing CategoryName!"));
    }
}
