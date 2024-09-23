package ru.arkhipov.MySecondTestAppSpingBoot.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {
    EMPTY(""),
    UNKNOWN("Неподдерживаемая ошибка"),
    UNSUPPORTED("Произошла непредвиденная ошибка"),
    VALIDATION("Ошибка валидации");
    private final String description;
    ErrorMessages(String description)
    {
        this.description = description;
    }
    @JsonValue
    public String getName()
    {
        return description;
    }

}
