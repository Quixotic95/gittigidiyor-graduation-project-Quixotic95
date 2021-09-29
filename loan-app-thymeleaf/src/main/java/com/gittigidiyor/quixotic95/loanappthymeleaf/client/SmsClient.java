package com.gittigidiyor.quixotic95.loanappthymeleaf.client;

import com.gittigidiyor.quixotic95.loanappthymeleaf.model.LoanApplicationResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "sms", url = "localhost:8080/api/sms")
public interface SmsClient {

    @PostMapping("/send")
    ResponseEntity<?> sendSms(@RequestBody LoanApplicationResult loanApplicationResult);

}
