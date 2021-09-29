package com.gittigidiyor.quixotic95.loanapp.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class LoanApplicationResultDTO {

    private String customerFirstName;

    private String customerLastName;

    private String customerPhoneNumber;

    private Double customerMonthlyIncome;

    private Instant createdDate;

    private Double creditLimit;

    private Boolean approved;

}
