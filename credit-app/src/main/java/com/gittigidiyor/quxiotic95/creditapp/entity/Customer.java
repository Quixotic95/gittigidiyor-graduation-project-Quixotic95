package com.gittigidiyor.quxiotic95.creditapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer extends BaseEntity {

    private String firstName;

    private String lastName;

    private double monthlyIncome;

    @EqualsAndHashCode.Include
    private String phoneNumber;

    @EqualsAndHashCode.Include
    private String tckn;

}