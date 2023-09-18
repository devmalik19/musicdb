package com.iceservices.musicdb.controller;

import com.iceservices.musicdb.service.ApiResponseService;
import org.springframework.beans.factory.annotation.Autowired;

public class ApiControllerBase
{
    @Autowired
    ApiResponseService apiResponseService;
}
