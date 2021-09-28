package com.gittigidiyor.quixotic95.loanappthymeleaf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LoanAppThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanAppThymeleafApplication.class, args);
    }

}
