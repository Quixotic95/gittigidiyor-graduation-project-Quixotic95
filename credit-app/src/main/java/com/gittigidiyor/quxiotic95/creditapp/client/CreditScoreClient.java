package com.gittigidiyor.quxiotic95.creditapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "creditScores", url = "localhost:8080/creditScores")
public interface CreditScoreClient {

    @PostMapping("{tckn}")
    ResponseEntity<?> generateCreditScore(@PathVariable String tckn);

}
