package com.studia.bd2.hotels.server.database.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import static java.util.Arrays.stream;

public enum CategoryName {
    FIRST,
    SECOND,
    THIRD;

    //TODO: need testing
    @JsonCreator
    public static CategoryName fromValue(String example) {
        return stream(values())
                .filter(v -> v.toString().equals(example.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(example + " does not match any existing CategoryName!"));
    }
}
