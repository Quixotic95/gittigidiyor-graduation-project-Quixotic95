package com.gittigidiyor.quixotic95.loanappthymeleaf.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ExceptionLog {

    private String timestamp;

    private String status;

    private String error;

    private String message;

    private String path;

}
