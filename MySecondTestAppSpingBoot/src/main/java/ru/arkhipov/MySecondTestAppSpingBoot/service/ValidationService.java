package ru.arkhipov.MySecondTestAppSpingBoot.service;

import org.springframework.validation.BindingResult;
import ru.arkhipov.MySecondTestAppSpingBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpingBoot.exception.ValidationFailedException;

public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
    void isSupportedUid(String Uid) throws UnsupportedCodeException;
}
