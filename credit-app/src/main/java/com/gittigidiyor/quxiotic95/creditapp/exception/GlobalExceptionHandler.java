package com.gittigidiyor.quxiotic95.creditapp.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.gittigidiyor.quxiotic95.creditapp.entity.log.ExceptionLog;
import com.gittigidiyor.quxiotic95.creditapp.mapper.ExceptionLogMapper;
import com.gittigidiyor.quxiotic95.creditapp.repository.ExceptionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Objects;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ExceptionLogRepository exceptionLogRepository;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, BindingResult result, HttpServletRequest req) {

        String errorMessage = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
        ExceptionLog exceptionLog = new ExceptionLog(errorMessage, HttpStatus.NOT_ACCEPTABLE, req.getRequestURI(), req.getMethod());
        exceptionLogRepository.save(exceptionLog);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest req) {

        String errorMessage = "";

        for (ConstraintViolation violations : e.getConstraintViolations()) {
            errorMessage = violations.getMessage();
        }

        ExceptionLog exceptionLog = new ExceptionLog(errorMessage, HttpStatus.NOT_ACCEPTABLE, req.getRequestURI(), req.getMethod());
        exceptionLogRepository.save(exceptionLog);

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest req) {

        ExceptionLog exceptionLog = new ExceptionLog("Potential error cause: HTTP Request Body is empty or misconfigured", HttpStatus.BAD_REQUEST, req.getRequestURI(), req.getMethod());
        exceptionLogRepository.save(exceptionLog);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

    }

    /*

        @ExceptionHandler(MissingPathVariableException.class)
        public ResponseEntity<?> handleMissingPathVariableException(MissingPathVariableException e) {

            ExceptionLog exceptionLog = new ExceptionLog(e.getMessage(), HttpStatus.BAD_REQUEST);
            exceptionLogRepository.save(exceptionLog);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

        }

        @ExceptionHandler(JsonParseException.class)
        public ResponseEntity<?> handleJsonParseException(JsonParseException e) {

            ExceptionLog exceptionLog = new ExceptionLog(e.getOriginalMessage(), HttpStatus.NOT_ACCEPTABLE);
            exceptionLogRepository.save(exceptionLog);

            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

        }

        @ExceptionHandler(InvalidFormatException.class)
        public ResponseEntity<?> handleInvalidFormatException(InvalidFormatException e) {

            ExceptionLog exceptionLog = new ExceptionLog(e.getOriginalMessage(), HttpStatus.NOT_ACCEPTABLE);
            exceptionLogRepository.save(exceptionLog);

            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

        }

        @ExceptionHandler(MismatchedInputException.class)
        public ResponseEntity<?> handleMismatchedInputException(MismatchedInputException e) {

            ExceptionLog exceptionLog = new ExceptionLog("JSON Object Type is incompatible", HttpStatus.NOT_ACCEPTABLE);
            exceptionLogRepository.save(exceptionLog);

            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

        }

        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {

            ExceptionLog exceptionLog = new ExceptionLog("The page you are looking for is not accessible.", HttpStatus.NOT_FOUND);
            exceptionLogRepository.save(exceptionLog);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));
        }

    */

    @ExceptionHandler(CustomerAlreadyExistsWithTCKNException.class)
    public ResponseEntity<?> handleCustomerAlreadyExistsWithTCKNException(CustomerAlreadyExistsWithTCKNException e, HttpServletRequest req) {

        ExceptionLog exceptionLog = new ExceptionLog(e.getMessage(), HttpStatus.CONFLICT, req.getRequestURI(), req.getMethod());
        exceptionLogRepository.save(exceptionLog);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

    }

    @ExceptionHandler(CustomerAlreadyExistsWithPhoneNumberException.class)
    public ResponseEntity<?> handleCustomerAlreadyExistsWithPhoneNumberException(CustomerAlreadyExistsWithPhoneNumberException e, HttpServletRequest req) {

        ExceptionLog exceptionLog = new ExceptionLog(e.getMessage(), HttpStatus.CONFLICT, req.getRequestURI(), req.getMethod());
        exceptionLogRepository.save(exceptionLog);

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest req) {

        ExceptionLog exceptionLog = new ExceptionLog(e.getMessage(), HttpStatus.NOT_FOUND, req.getRequestURI(), req.getMethod());
        exceptionLogRepository.save(exceptionLog);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionLogMapper.INSTANCE.toExceptionLogDto(exceptionLog));

    }

}
