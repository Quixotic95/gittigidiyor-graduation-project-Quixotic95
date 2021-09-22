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

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e) {

        String errorMessage = "";

        for (ConstraintViolation violations : e.getConstraintViolations()) {
            errorMessage = violations.getMessage();
        }

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

    @ExceptionHandler(CustomerAlreadyExistsWithTCKNException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> handleCustomerAlreadyExistsWithTCKNException(CustomerAlreadyExistsWithTCKNException e) {

        ExceptionLog exceptionLog = new ExceptionLog(e.getMessage(), HttpStatus.CONFLICT);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

    }

    @ExceptionHandler(CustomerAlreadyExistsWithPhoneNumberException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<?> handleCustomerAlreadyExistsWithPhoneNumberException(CustomerAlreadyExistsWithPhoneNumberException e) {

        ExceptionLog exceptionLog = new ExceptionLog(e.getMessage(), HttpStatus.CONFLICT);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {

        ExceptionLog exceptionLog = new ExceptionLog(e.getMessage(), HttpStatus.NOT_FOUND);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

    }

}
