package edu.wut.hotels.management.database.entity;

import java.util.Arrays;
import java.util.Collection;

public interface Identifiable {
    Long getId();
    void setId(Long value);

    static <T extends Identifiable> String extractIds(Collection<T> t) {
        return Arrays.toString(t.stream().map(T::getId).toArray());
    }
}
