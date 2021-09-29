package com.gittigidiyor.quixotic95.loanapp.controller;

import com.gittigidiyor.quixotic95.loanapp.dto.LoanApplicationResultDTO;
import com.gittigidiyor.quixotic95.loanapp.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("api/loanApplications")
@Validated
@RequiredArgsConstructor
@Slf4j
public class LoanApplicationRestController {

    private final LoanApplicationService loanApplicationService;

    @PostMapping("{tckn}")
    public ResponseEntity<?> applyCustomerForLoanByTckn(@PathVariable(name = "tckn")
                                                  @NotBlank
                                                  @Pattern(regexp = "^[1-9][0-9]{9}[02468]$", message = "TCKN can not start with 0, can not end with odd number and must contain 11 numbers!")
                                                          String tckn) {

        log.info("inside LoanApplicationRestController applyCustomerForLoanByTckn");
        log.info("tckn: " + tckn);

        LoanApplicationResultDTO result = loanApplicationService.applyForLoanByTckn(tckn);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{tckn}")
    public ResponseEntity<?> getCustomerLoanApplicationResultsByTckn(@PathVariable(name = "tckn")
                                                              @NotBlank
                                                              @Pattern(regexp = "^[1-9][0-9]{9}[02468]$", message = "TCKN can not start with 0, can not end with odd number and must contain 11 numbers!")
                                                                      String tckn) {

        log.info("inside LoanApplicationRestController getCustomerLoanApplicationResultsByTckn");
        log.info("tckn: " + tckn);

        List<LoanApplicationResultDTO> result = loanApplicationService.findLoanApplicationsByTckn(tckn);
        return ResponseEntity.ok(result);
    }

    @GetMapping("getLast/{tckn}")
    public ResponseEntity<?> getLastLoanApplicationResultOfCustomerByTckn(@PathVariable(name = "tckn")
                                                                          @NotBlank
                                                                          @Pattern(regexp = "^[1-9][0-9]{9}[02468]$", message = "TCKN can not start with 0, can not end with odd number and must contain 11 numbers!")
                                                                                  String tckn) {

        log.info("inside LoanApplicationRestController getLastLoanApplicationResultOfCustomerByTckn");
        log.info("tckn: " + tckn);

        LoanApplicationResultDTO result = loanApplicationService.findLastLoanApplicationResultOfCustomerByTckn(tckn);
        return ResponseEntity.ok(result);
    }

}
