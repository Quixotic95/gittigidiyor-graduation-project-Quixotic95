package com.gittigidiyor.quixotic95.loanapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOAN_APPLICATION_RESULT")
public class LoanApplicationResult extends BaseEntityWithLongId {

    @Column(name = "CUSTOMER_TCKN")
    private String customerTckn;

    @Column(name = "CUSTOMER_FIRST_NAME")
    private String customerFirstName;

    @Column(name = "CUSTOMER_LAST_NAME")
    private String customerLastName;

    @Column(name = "CUSTOMER_PHONE_NUMBER")
    private String customerPhoneNumber;

    @Column(name = "CUSTOMER_MONTHLY_INCOME")
    private double customerMonthlyIncome;

    @Column(name = "CREDIT_LIMIT")
    private double creditLimit;

    @Column(name = "APPROVED")
    private boolean isApproved;

}
