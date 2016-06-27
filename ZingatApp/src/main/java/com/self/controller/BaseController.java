package com.self.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by akash.p on 24/6/16.
 */
public class BaseController {

    @ExceptionHandler
    public String handleException(Exception e){
        return "Exception Occurred";
    }
}
