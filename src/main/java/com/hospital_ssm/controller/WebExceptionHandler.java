package com.hospital_ssm.controller;

import com.hospital_ssm.util.HttpCodeUtil;
import com.hospital_ssm.util.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public R handleException(Exception e) {
        e.printStackTrace();
        return new R(Integer.valueOf(HttpCodeUtil.EXCEPTION.toString()), null, e.getMessage());
    }
}
