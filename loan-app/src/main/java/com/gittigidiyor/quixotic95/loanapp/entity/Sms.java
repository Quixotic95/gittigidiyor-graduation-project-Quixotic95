package com.gittigidiyor.quixotic95.loanapp.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sms {

    @CreatedDate
    private Instant createdDate;

    private String message;

    private String sendTo;

    public Sms(String firstName, String lastName, Instant createdDate, Boolean isApproved, Double limit, String sendTo) {

        LocalDateTime dateTime = LocalDateTime.ofInstant(createdDate, ZoneId.systemDefault());

        String dateAndHour = dateTime.getDayOfMonth() + " " +
                dateTime.getMonth().getDisplayName(TextStyle.FULL, new Locale("tr")) + " " +
                dateTime.getYear() + " " +
                (dateTime.getHour() < 10 ? '0' : "") + dateTime.getHour() + ":" +
                (dateTime.getMinute() < 10 ? '0' : "") + dateTime.getMinute();

        String approveType = (isApproved ? "onaylanmış" : "reddedilmiş");

        this.message = "Sayın " + firstName + " " + lastName + ", " + dateAndHour + " tarihinde yapmış olduğunuz başvurunuz " + approveType + " olup;" +
                " tarafınıza tanımlanan kredi limiti: " + limit + " olarak belirlenmiştir.";

        this.sendTo = sendTo;
    }
}
