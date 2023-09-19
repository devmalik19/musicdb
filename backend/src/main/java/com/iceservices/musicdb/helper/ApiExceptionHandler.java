package com.iceservices.musicdb.helper;

import com.iceservices.musicdb.config.Config;
import com.iceservices.musicdb.content.ExceptionMessage;
import com.iceservices.musicdb.data.dto.ApiResponseContainer;
import com.iceservices.musicdb.data.exception.InvalidDataException;
import com.iceservices.musicdb.data.exception.ResourceNotFoundException;
import com.iceservices.musicdb.service.ApiResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

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
        String message;
        if(config.isDebug())
        {
            message  =   e.getMessage();
            e.printStackTrace();
        }
        else
        {
            message  =  exceptionMessage.getDefaultMessage();
        }
        return apiResponseService.prepareErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseContainer> resourceNotFoundException(ResourceNotFoundException e)
    {
        return apiResponseService.prepareErrorResponse(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiResponseContainer> invalidDataException(InvalidDataException e)
    {
        return apiResponseService.prepareErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
