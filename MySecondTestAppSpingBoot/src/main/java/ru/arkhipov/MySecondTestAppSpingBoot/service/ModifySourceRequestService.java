package ru.arkhipov.MySecondTestAppSpingBoot.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.arkhipov.MySecondTestAppSpingBoot.model.Request;
import ru.arkhipov.MySecondTestAppSpingBoot.util.DateTimeUtil;

import java.util.Date;

@Service
@Qualifier("ModifySourceRequestService")
public class ModifySourceRequestService implements ModifyRequestService {
    @Override
    public void modify(Request request)
    {
        request.setSource("Source 1");
        HttpEntity<Request> httpEntity = new HttpEntity<>(request);
        request.setSystemTime(DateTimeUtil.getCustomFormat().format(new Date()));
        new RestTemplate().exchange("http://localhost:8084/feedback",
                HttpMethod.POST, httpEntity, new ParameterizedTypeReference<>() {
                });

    }
}
