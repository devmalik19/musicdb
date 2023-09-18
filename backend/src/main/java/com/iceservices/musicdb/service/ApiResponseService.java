package com.iceservices.musicdb.service;

import com.iceservices.musicdb.data.dto.ApiResponseContainer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ApiResponseService
{
    public ResponseEntity<ApiResponseContainer> prepareApiResponse(Object responseObject)
    {
        ApiResponseContainer apiResponseContainer = new ApiResponseContainer();
        apiResponseContainer.setData(responseObject);
        apiResponseContainer.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponseContainer, HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseContainer> prepareErrorResponse(String message, HttpStatus httpStatus)
    {
        ApiResponseContainer apiResponseContainer = new ApiResponseContainer();
        apiResponseContainer.setError(message);
        apiResponseContainer.setStatus(httpStatus.value());
        return new ResponseEntity<>(apiResponseContainer, httpStatus);
    }
}
