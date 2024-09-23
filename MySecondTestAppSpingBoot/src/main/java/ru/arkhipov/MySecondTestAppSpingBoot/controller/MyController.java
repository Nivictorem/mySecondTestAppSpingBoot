package ru.arkhipov.MySecondTestAppSpingBoot.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.arkhipov.MySecondTestAppSpingBoot.exception.UnsupportedCodeException;
import ru.arkhipov.MySecondTestAppSpingBoot.exception.ValidationFailedException;
import ru.arkhipov.MySecondTestAppSpingBoot.model.*;
import ru.arkhipov.MySecondTestAppSpingBoot.service.ModifyResponseService;
import ru.arkhipov.MySecondTestAppSpingBoot.service.ModifySystemTimeResponseService;
import ru.arkhipov.MySecondTestAppSpingBoot.service.ValidationService;
import ru.arkhipov.MySecondTestAppSpingBoot.util.DateTimeUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
public class MyController {
    private final ValidationService validationService;
    private final ModifyResponseService modifyResponseService;
    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService")ModifySystemTimeResponseService modifySystemTimeResponseService)
    {
        this.validationService = validationService;
        this.modifyResponseService = modifySystemTimeResponseService;
    }
    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult)
    {
        log.info("request: {}",request);
        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCES)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();
        log.info("response: {}",response);
        try {
            validationService.isValid(bindingResult);
            validationService.isSupportedUid(request.getUid());
        } catch (ValidationFailedException e)
        {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION);
            log.error("Error mess:", e);
            return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (UnsupportedCodeException e){
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED);
            log.error("Error mess:", e);
        }catch (Exception e)
        {
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN);
            log.error("Error mess:", e);
            return  new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        modifyResponseService.modify(response);
        log.info("response: {}",response);
        return new ResponseEntity<>(modifyResponseService.modify(response), HttpStatus.OK);
    }
}
