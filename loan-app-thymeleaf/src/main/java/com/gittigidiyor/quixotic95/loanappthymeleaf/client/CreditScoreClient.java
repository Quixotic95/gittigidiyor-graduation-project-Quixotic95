package com.gittigidiyor.quixotic95.loanappthymeleaf.client;

import com.gittigidiyor.quixotic95.loanappthymeleaf.model.CreditScore;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "creditScores", url = "localhost:8080/api/creditScores")
public interface CreditScoreClient {

    @PostMapping("{tckn}")
    ResponseEntity<CreditScore> generateCreditScoreByTckn(@PathVariable(name = "tckn") String tckn);

}
