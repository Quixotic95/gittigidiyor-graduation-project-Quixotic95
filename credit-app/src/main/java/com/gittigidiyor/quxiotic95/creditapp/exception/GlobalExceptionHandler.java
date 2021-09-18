package com.gittigidiyor.quxiotic95.creditapp.exception;

import com.gittigidiyor.quxiotic95.creditapp.entity.log.ExceptionLog;
import com.gittigidiyor.quxiotic95.creditapp.mapper.ExceptionLogMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, BindingResult result) {
        String errorMessage = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
        ExceptionLog exceptionLog = new ExceptionLog(errorMessage, HttpStatus.NOT_ACCEPTABLE);
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ExceptionLog exceptionLog = new ExceptionLog("Potential error cause: HTTP Body is empty or misconfigured", HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleMissingPathVariableException(MissingPathVariableException e) {
        ExceptionLog exceptionLog = new ExceptionLog(e.getMessage(), HttpStatus.BAD_REQUEST);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));
    }

}
