package com.gittigidiyor.quixotic95.loanappthymeleaf.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Getter
@Setter
public class LoanApplicationResult {

    private String customerFirstName;

    private String customerLastName;

    private String customerPhoneNumber;

    private Double customerMonthlyIncome;

    private Instant createdDate;

    private Double creditLimit;

    private Boolean approved;

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
