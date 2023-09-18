package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dto.ApiResponseContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiResponseService
{
    Logger logger = LoggerFactory.getLogger(ApiResponseService.class.getName());
    public enum TYPE{CREATED, REMOVED};

    public ResponseEntity<ApiResponseContainer> prepareApiResponse(Object responseObject)
    {
        ApiResponseContainer apiResponseContainer = new ApiResponseContainer();
        apiResponseContainer.setData(responseObject);
        apiResponseContainer.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponseContainer, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseContainer> prepareApiResponse(String message, TYPE type)
    {
        ApiResponseContainer apiResponseContainer = new ApiResponseContainer();
        apiResponseContainer.setData(message);
        HttpStatus httpStatus = HttpStatus.OK;

        switch (type)
        {
            case CREATED :
                httpStatus = HttpStatus.CREATED;
                break;
            case REMOVED :
                httpStatus = HttpStatus.OK;
                break;
        }

        apiResponseContainer.setStatus(httpStatus.value());
        return new ResponseEntity<>(apiResponseContainer, httpStatus);
    }

    public ResponseEntity<ApiResponseContainer> prepareApiResponse(List<?> list)
    {
        ApiResponseContainer apiResponseContainer = new ApiResponseContainer();
        HttpStatus httpStatus = HttpStatus.OK;
        if(list.isEmpty())
        {
            httpStatus = HttpStatus.NO_CONTENT;
        }
        else
        {
            apiResponseContainer.setData(list);
        }
        apiResponseContainer.setStatus(httpStatus.value());
        return new ResponseEntity<>(apiResponseContainer, httpStatus);
    }

    public ResponseEntity<ApiResponseContainer> prepareErrorResponse(String message, HttpStatus httpStatus, Exception e)
    {
        logger.error(e.getMessage());
        ApiResponseContainer apiResponseContainer = new ApiResponseContainer();
        apiResponseContainer.setError(message);
        apiResponseContainer.setStatus(httpStatus.value());
        return new ResponseEntity<>(apiResponseContainer, httpStatus);
    }

    public ResponseEntity<ApiResponseContainer> prepareErrorResponse(String message, HttpStatus httpStatus)
    {
        ApiResponseContainer apiResponseContainer = new ApiResponseContainer();
        apiResponseContainer.setError(message);
        apiResponseContainer.setStatus(httpStatus.value());
        return new ResponseEntity<>(apiResponseContainer, httpStatus);
    }
}
