package com.gittigidiyor.quixotic95.loanapp.entity.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "EXCEPTION_LOG")
public class ExceptionLog extends BaseLogEntity {

    private int status;

    private String path;

    private String methodType;

    private String error;

    private String message;

    public ExceptionLog(String message, HttpStatus type, String path, String methodType) {
        this.message = message;
        this.status = type.value();
        this.error = type.name();
        this.path = path;
        this.methodType = methodType;
    }

}
