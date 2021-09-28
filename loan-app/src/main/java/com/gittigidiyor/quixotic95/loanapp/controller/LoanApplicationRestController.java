package com.gittigidiyor.quixotic95.loanapp.controller;

import com.gittigidiyor.quixotic95.loanapp.dto.LoanApplicationResultDTO;
import com.gittigidiyor.quixotic95.loanapp.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
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
public class LoanApplicationRestController {

    private final LoanApplicationService loanApplicationService;

    @PostMapping("{tckn}")
    public ResponseEntity<?> applyCustomerForLoan(@PathVariable(name = "tckn")
                                                  @NotBlank
                                                  @Pattern(regexp = "^[1-9][0-9]{9}[02468]$", message = "TCKN can not start with 0, can not end with odd number and must contain 11 numbers!")
                                                          String tckn) {
        LoanApplicationResultDTO result = loanApplicationService.applyForLoan(tckn);
        return ResponseEntity.ok(result);
    }

    @GetMapping("{tckn}")
    public ResponseEntity<?> getCustomerLoanApplicationResult(@PathVariable(name = "tckn")
                                                        @NotBlank
                                                        @Pattern(regexp = "^[1-9][0-9]{9}[02468]$", message = "TCKN can not start with 0, can not end with odd number and must contain 11 numbers!")
                                                                String tckn) {
        List<LoanApplicationResultDTO> result = loanApplicationService.findLoanApplications(tckn);
        return ResponseEntity.ok(result);
    }

}
