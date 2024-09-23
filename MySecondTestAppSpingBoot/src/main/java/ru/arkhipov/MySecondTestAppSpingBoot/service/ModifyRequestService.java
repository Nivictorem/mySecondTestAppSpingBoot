package ru.arkhipov.MySecondTestAppSpingBoot.service;

import org.springframework.stereotype.Service;
import ru.arkhipov.MySecondTestAppSpingBoot.model.Request;
@Service
public interface ModifyRequestService {
    void modify(Request request);
}