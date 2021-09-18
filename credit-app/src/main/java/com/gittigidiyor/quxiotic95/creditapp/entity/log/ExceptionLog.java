package com.gittigidiyor.quxiotic95.creditapp.entity.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ExceptionLog extends BaseLogEntity {

    private int status;

    private String error;

    private String message;

    public ExceptionLog(String message, HttpStatus type) {
        this.message = message;
        this.status = type.value();
        this.error = type.name();
    }

}
