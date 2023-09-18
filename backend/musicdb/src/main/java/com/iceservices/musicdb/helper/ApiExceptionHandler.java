package com.iceservices.musicdb.helper;

import com.iceservices.musicdb.config.Config;
import com.iceservices.musicdb.content.ExceptionMessage;
import com.iceservices.musicdb.data.dto.ApiResponseContainer;
import com.iceservices.musicdb.service.ApiResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler
{
    @Autowired
    private Config config;

    @Autowired
    private ExceptionMessage exceptionMessage;

    @Autowired
    private ApiResponseService apiResponseService;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseContainer> catchAllExceptions(Exception e)
    {
        String message  =   config.isDebug() ? e.getMessage(): exceptionMessage.getDefaultMessage();
        return apiResponseService.prepareErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
