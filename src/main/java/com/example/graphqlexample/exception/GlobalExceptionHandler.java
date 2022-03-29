package com.example.graphqlexample.exception;

import graphql.kickstart.spring.error.ThrowableGraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ThrowableGraphQLError throwableGraphQLError(RuntimeException e){
        log.error("error : ",e);
        return new ThrowableGraphQLError(new RuntimeException("서버에 오류가 발생했습니다. 오류코드 C001",e.getCause()));
    }
}