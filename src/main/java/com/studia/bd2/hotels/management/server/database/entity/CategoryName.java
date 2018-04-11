package com.studia.bd2.hotels.management.server.database.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.util.StringUtils;

enum CategoryName {
    FIRST,
    SECOND,
    THIRD;


    @JsonCreator
    public CategoryName fromString(String category) {
        if(StringUtils.isEmpty(category)) {
            throw new IllegalArgumentException("Cannot create category from: " + category);
        }

        return CategoryName.valueOf(category);
    }
}
