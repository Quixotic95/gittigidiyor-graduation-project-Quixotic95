package com.gittigidiyor.quixotic95.loanapp.entity.log;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Clock;
import java.time.Instant;

@Data
@MappedSuperclass
public abstract class BaseLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOG_ID")
    private long id;


    @CreatedDate
    @Column(name = "TIMESTAMP", updatable = false)
    private Instant timestamp = Instant.now(Clock.systemUTC());
}
