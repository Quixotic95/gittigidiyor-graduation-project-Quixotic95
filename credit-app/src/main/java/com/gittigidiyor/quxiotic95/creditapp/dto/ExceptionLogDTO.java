package com.gittigidiyor.quxiotic95.creditapp.dto;

import com.gittigidiyor.quxiotic95.creditapp.dto.generic.GenericDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionLogDTO implements GenericDTO<ExceptionLogDTO> {

    private Instant timestamp;

    private int status;

    private String error;

    private String message;

}
