package com.gittigidiyor.quxiotic95.creditapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditApplicationResult extends BaseEntityWithLongId {

    private String customerTckn;

    private String customerFirstName;

    private String customerLastName;

    private String customerPhoneNumber;

    private double customerMonthlyIncome;

    private double creditLimit;

    private boolean isApproved;

}
