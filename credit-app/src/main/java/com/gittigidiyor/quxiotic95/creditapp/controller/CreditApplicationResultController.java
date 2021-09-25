package com.gittigidiyor.quxiotic95.creditapp.controller;

import com.gittigidiyor.quxiotic95.creditapp.dto.CreditApplicationResultDTO;
import com.gittigidiyor.quxiotic95.creditapp.service.CreditApplicationResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("creditApplicationResults")
@RequiredArgsConstructor
public class CreditApplicationResultController {

    private final CreditApplicationResultService creditApplicationResultService;

    @PostMapping
    public ResponseEntity<?> applyCustomerForLoan(String tckn) {
        CreditApplicationResultDTO result = creditApplicationResultService.applyForLoan(tckn);
        return ResponseEntity.ok(result);
    }

}
