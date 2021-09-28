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
@Table(name = "CUSTOMER")
public class Customer extends BaseEntityWithUUID {

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MONTHLY_INCOME")
    private Double monthlyIncome;

    @EqualsAndHashCode.Include
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @EqualsAndHashCode.Include
    @Column(name = "TCKN")
    private String tckn;

}