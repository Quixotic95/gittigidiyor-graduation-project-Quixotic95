package com.gittigidiyor.quixotic95.loanappthymeleaf.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class LoanApplicationResult {

    private Double creditLimit;

    private Double customerMonthlyIncome;

    private Boolean approved;

    private Instant createdDate;

    private String applicationDate;

    public String getApplicationDate() {
        LocalDateTime dateTime = LocalDateTime.ofInstant(this.createdDate, ZoneId.systemDefault());

        return dateTime.getDayOfMonth() + " " +
                dateTime.getMonth() + " " +
                dateTime.getYear() + " " +
                (dateTime.getHour() < 10 ? '0' : "") + dateTime.getHour() + ":" +
                (dateTime.getMinute() < 10 ? '0' : "") + dateTime.getMinute();

    }

}
