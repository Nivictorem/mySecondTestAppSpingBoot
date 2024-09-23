package ru.arkhipov.MySecondTestAppSpingBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpingBoot.model.Response;

@Service
public interface ModifyResponseService {
    Response modify(Response response);
}
