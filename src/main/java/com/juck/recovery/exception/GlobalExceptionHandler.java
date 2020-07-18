package com.juck.recovery.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ErrorInfo.class)
    public Map errorInfoHandler(ErrorInfo ex) {
        Map map = new HashMap();
        map.put("code", ex.getCode());
        map.put("msg", ex.getMessage());
        return map;
    }

    @ExceptionHandler(value = Exception.class)
    public Map exceptionHandler(Exception ex) {
        Map map = new HashMap();
        map.put("code", 400);
        map.put("msg", ex.getMessage());
        return map;
    }
}
