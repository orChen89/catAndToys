package com.catandtoys.catAndToys.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Constraint {


    ENTITY_NOT_EXISTS(" is not exists!"),

    ENTITY_ALREADY_EXISTS(" is already exists!"),

    INVALID_WEIGHT("Invalid input - Please enter according to the correct format");

    @Getter
    private final String errorMsg;
}
