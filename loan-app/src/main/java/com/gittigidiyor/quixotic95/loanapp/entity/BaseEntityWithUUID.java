package com.gittigidiyor.quixotic95.loanapp.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityWithUUID {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE", nullable = false)
    private Instant lastModifiedDate;

}
