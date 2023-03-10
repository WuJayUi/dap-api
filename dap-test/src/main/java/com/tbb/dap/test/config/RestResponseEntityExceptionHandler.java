package com.tbb.dap.test.config;


import com.tbb.dap.common.exception.BaseException;
import com.tbb.dap.common.exception.CommonException;
import com.tbb.dap.common.utils.ConvertUtil;
import com.tbb.dap.test.controller.common.BaseRs;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CommonException.class})
    protected ResponseEntity<BaseRs<Object>> handleConflict(
            BaseException ex, WebRequest request) {
        log.info("req attr:{}", ConvertUtil.toJson(request.getAttributeNames(1)));
        log.info("rq: {}", ConvertUtil.toJson(request.getParameterMap()));
        log.info("Handler errCode: {}", ex.getErrCode());
        return BaseRs.error(ex);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<?> allHandleConflict(
            Exception ex, WebRequest request) {
        log.info("rq: {}", ConvertUtil.toJson(request.getParameterMap()));
        ex.printStackTrace();
        return BaseRs.error(ex);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> errMap.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errMap, status);
    }

}
