package com.gittigidiyor.quixotic95.loanapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LoanAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanAppApplication.class, args);
    }

}
