package com.gittigidiyor.quxiotic95.creditapp.dto;

import com.gittigidiyor.quxiotic95.creditapp.dto.generic.GenericDTO;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
public class ExceptionLogDTO implements GenericDTO<ExceptionLogDTO> {

    private Instant timestamp;

    private int status;

    private String error;

    private String message;

}
