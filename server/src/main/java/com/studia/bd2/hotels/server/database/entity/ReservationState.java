package com.studia.bd2.hotels.server.database.entity;

import com.fasterxml.jackson.annotation.JsonCreator;

import static java.util.Arrays.stream;

public enum ReservationState {
    MADE,        // reservation was made
    PAID,        // was paid
    IN_PROGRESS, // stay in the root is in progress
    FINISHED;    // guests left the room.            probably need discussion


    //TODO: need testing
    @JsonCreator
    public static ReservationState fromValue(String example) {
        return stream(values())
                .filter(v -> v.toString().equals(example.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(example + " does not match any existing CategoryName!"));
    }
}
