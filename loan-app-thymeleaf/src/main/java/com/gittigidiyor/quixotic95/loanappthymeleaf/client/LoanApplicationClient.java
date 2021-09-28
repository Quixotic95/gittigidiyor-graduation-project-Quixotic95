package com.gittigidiyor.quixotic95.loanappthymeleaf.client;

import com.gittigidiyor.quixotic95.loanappthymeleaf.model.LoanApplicationResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(value = "loanApplications", url = "localhost:8080/api/loanApplications")
public interface LoanApplicationClient {

    @PostMapping("{tckn}")
    ResponseEntity<LoanApplicationResult> applyCustomerForLoan(@PathVariable(name = "tckn") String tckn);

    @GetMapping("{tckn}")
    ResponseEntity<List<LoanApplicationResult>> getCustomerLoanApplicationResults(@PathVariable(name = "tckn") String tckn);

    @GetMapping("getLast/{tckn}")
    ResponseEntity<LoanApplicationResult> getLastLoanApplicationResultOfCustomerByTckn(@PathVariable(name = "tckn") String tckn);

}
