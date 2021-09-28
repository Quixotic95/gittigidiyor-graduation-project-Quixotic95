package com.gittigidiyor.quixotic95.loanapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CREDIT_SCORE")
public class CreditScore extends BaseEntityWithLongId {

    @EqualsAndHashCode.Include
    @Column(name = "CUSTOMER_TCKN")
    private String customerTckn;

    @Column(name = "CREDIT_SCORE")
    private double creditScore;

}
