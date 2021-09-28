package com.gittigidiyor.quixotic95.loanappthymeleaf.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gittigidiyor.quixotic95.loanappthymeleaf.model.ExceptionLog;
import feign.FeignException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ThymeleafExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ModelAndView handleRuntimeException(FeignException e, HttpServletRequest request) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();

        ExceptionLog exceptionLog = mapper.readValue(e.contentUTF8(), ExceptionLog.class);
        exceptionLog.setPath(request.getRequestURI());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("exceptionLog", exceptionLog);
        modelAndView.setViewName("errors/error");

        return modelAndView;
    }

}
