package com.enba.integrate.resttemplate.service;

import com.enba.integrate.resttemplate.exception.ClientErrorException;
import com.enba.integrate.resttemplate.handler.CustomResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;

@Service
public class MyService {

    private final RestTemplate restTemplate;

    public MyService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.errorHandler(new CustomResponseErrorHandler()).build();
    }

    public String callService(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (ClientErrorException | ServerErrorException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    static class ServiceException extends RuntimeException {
        public ServiceException(String message) {
            super(message);
        }
    }
}