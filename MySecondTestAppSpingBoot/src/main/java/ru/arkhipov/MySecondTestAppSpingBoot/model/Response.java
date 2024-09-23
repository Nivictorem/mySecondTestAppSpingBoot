package ru.arkhipov.MySecondTestAppSpingBoot.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {
    private String uid;
    private String operationUid;
    private String systemTime;
    private Codes code;
    private ErrorCodes errorCode;
    private ErrorMessages errorMessage;
    @Override
    public String toString()
    {
        return "{" +
                "uid'" + uid + '\'' +
                ", operationUid'" + operationUid + '\'' +
                ", systemTime'" + systemTime + '\'' +
                ", code'" + code.toString() + '\'' +
                ", errorCode'" + errorCode + '\'' +
                ", errorMessage'" + errorMessage + "}";
    }
}
