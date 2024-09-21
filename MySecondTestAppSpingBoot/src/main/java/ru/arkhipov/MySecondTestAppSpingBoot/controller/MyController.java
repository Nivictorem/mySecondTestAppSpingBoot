package ru.arkhipov.MySecondTestAppSpingBoot.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.arkhipov.MySecondTestAppSpingBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpingBoot.exception.ValidationFailedException;
import ru.arkhipov.MySecondTestAppSpingBoot.model.Request;
import ru.arkhipov.MySecondTestAppSpingBoot.model.Response;
import ru.arkhipov.MySecondTestAppSpingBoot.service.ValidationService;

import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
public class MyController {
    private final ValidationService validationService;

    @Autowired
    public MyController(ValidationService validationService)
    {
        this.validationService = validationService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(simpleDateFormat.format(new Date()))
                .code("success")
                .errorCode("")
                .errorMessage("")
                .build();
        try {
            validationService.isValid(bindingResult);
            validationService.isSupportedUid(request.getUid());
        } catch (ValidationFailedException e)
        {
            response.setCode("failed");
            response.setErrorCode("ValidationException");
            response.setErrorMessage("Ошибка валидации");
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (UnsupportedCodeException e){
            response.setCode("failed");
            response.setErrorCode("Unsupported code exception");
            response.setErrorMessage(e.getMessage());
        }catch (Exception e)
        {
            response.setCode("failed");
            response.setErrorCode("UnknowingException");
            response.setErrorMessage("произошла непредвиденная ошибка");
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
