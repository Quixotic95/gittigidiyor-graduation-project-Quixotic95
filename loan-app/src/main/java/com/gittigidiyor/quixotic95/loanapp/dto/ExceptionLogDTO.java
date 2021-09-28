package com.gittigidiyor.quixotic95.loanapp.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
public class ExceptionLogDTO {

    private Instant timestamp;

    private Integer status;

    private String error;

    private String message;

}
