package com.gittigidiyor.quixotic95.loanapp.controller;

import com.gittigidiyor.quixotic95.loanapp.dto.CreditScoreDTO;
import com.gittigidiyor.quixotic95.loanapp.service.CreditScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("api/creditScores")
@Validated
@RequiredArgsConstructor
@Slf4j
public class CreditScoreRestController {

    private final CreditScoreService creditScoreService;

    @PostMapping("{tckn}")
    public ResponseEntity<?> generateCreditScoreByTckn(@PathVariable(name = "tckn")
                                                       @NotBlank
                                                       @Pattern(regexp = "^[1-9][0-9]{9}[02468]$", message = "TCKN can not start with 0, can not end with odd number and must contain 11 numbers!")
                                                               String tckn) {

        log.info("inside CreditScoreRestController generateCreditScoreByTckn");
        log.info("tckn: " + tckn);

        CreditScoreDTO result = creditScoreService.generateCreditScoreByTckn(tckn);

        return ResponseEntity.ok(result);

    }

}
