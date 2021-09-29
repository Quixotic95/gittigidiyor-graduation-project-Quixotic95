package com.gittigidiyor.quixotic95.loanapp.controller;

import com.gittigidiyor.quixotic95.loanapp.entity.LoanApplicationResult;
import com.gittigidiyor.quixotic95.loanapp.entity.Sms;
import com.gittigidiyor.quixotic95.loanapp.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/sms")
@RequiredArgsConstructor
@Slf4j
public class SmsRestController {

    private final SmsService smsService;

    @PostMapping("/send")
    public ResponseEntity<?> sendSms(@RequestBody LoanApplicationResult loanApplicationResult) {

        log.info("inside SmsRestController sendSms");
        log.info("loanApplicationResult: " + loanApplicationResult.toString());

        Sms result = smsService.sendSms(loanApplicationResult);
        return ResponseEntity.ok(result);

    }

}
