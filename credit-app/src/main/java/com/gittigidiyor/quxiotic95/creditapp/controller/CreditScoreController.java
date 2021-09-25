package com.gittigidiyor.quxiotic95.creditapp.controller;

import com.gittigidiyor.quxiotic95.creditapp.dto.CreditScoreDTO;
import com.gittigidiyor.quxiotic95.creditapp.service.CreditScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("creditScores")
@Validated
@RequiredArgsConstructor
public class CreditScoreController {

    private final CreditScoreService creditScoreService;

    @PostMapping("{tckn}")
    public ResponseEntity<?> generateCreditScoreByTckn(@PathVariable(name = "tckn")
                                                       @NotBlank
                                                       @Pattern(regexp = "^[1-9][0-9]{9}[02468]$", message = "TCKN can not start with 0, can not end with odd number and must contain 11 numbers!")
                                                               String tckn) {

        CreditScoreDTO result = creditScoreService.generateCreditScore(tckn);

        return ResponseEntity.ok(result);

    }

}
