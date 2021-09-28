package com.gittigidiyor.quixotic95.loanapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class LoanApplicationResultDTO {

    private Double creditLimit;

    private Double customerMonthlyIncome;

    private Boolean approved;

    private Instant createdDate;

}
