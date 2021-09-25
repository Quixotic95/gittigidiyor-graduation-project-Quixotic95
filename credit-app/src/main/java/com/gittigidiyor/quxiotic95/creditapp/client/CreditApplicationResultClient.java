package com.gittigidiyor.quxiotic95.creditapp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "creditApplicationResults", url = "localhost:8080/creditApplicationResults")
public interface CreditApplicationResultClient {

    @PostMapping
    ResponseEntity<?> applyForLoan(String tckn);

}
