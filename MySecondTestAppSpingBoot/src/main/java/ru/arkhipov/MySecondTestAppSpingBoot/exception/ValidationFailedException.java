package ru.arkhipov.MySecondTestAppSpingBoot.exception;

public class ValidationFailedException extends  Exception{
    public  ValidationFailedException(String message)
    {
        super(message);
    }
}
