package ru.arkhipov.MySecondTestAppSpingBoot.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    @NotBlank
    @Size(max = 32)
    private String uid;
    @NotBlank
    @Size(max = 32)
    private String operationUid;
    private Systems systemName;
    @NotBlank
    private String systemTime;
    private String source;
    @Max(100000)
    @Min(1)
    private int communication;
    private int tamplateId;
    private int productionCode;
    private int smsCode;

    @Override
    public String toString()
    {
        return "{" +
                "uid'" + uid + '\'' +
                ", operationUid'" + operationUid + '\'' +
                ", systemName''" + systemName + '\'' +
                ", systemTime'" + systemTime + '\'' +
                ", source'" + source + '\'' +
                ", communication'" + communication + '\'' +
                ", tamplateId'" + tamplateId + '\'' +
                ", productionCode'" + productionCode + '\'' +
                ", smsCode'" + smsCode + '\'' + "}";
    }
}
