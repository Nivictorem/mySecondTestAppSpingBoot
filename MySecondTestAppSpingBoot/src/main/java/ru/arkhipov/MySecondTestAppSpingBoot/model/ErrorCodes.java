package ru.arkhipov.MySecondTestAppSpingBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorCodes {
    EMPTY(""),
    UNKNOWN_EXCEPTION("unknownException"),
    UNSUPPORTED_EXCEPTION("unsupportedException"),
    VALIDATION_EXCEPTION("validationException");
    private final String name;
    ErrorCodes(String name)
    {
        this.name = name;
    }
    @JsonValue
    public String getName()
    {
        return name;
    }
    @Override
    public String toString()
    {
        return name;
    }
}
