package com.gittigidiyor.quxiotic95.creditapp.controller;

import com.gittigidiyor.quxiotic95.creditapp.dto.CustomerDTO;
import com.gittigidiyor.quxiotic95.creditapp.service.implementation.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    @GetMapping
    public ResponseEntity<?> sayHello() {
        return ResponseEntity.ok("Welcome to landing customer page!");
    }

    @PostMapping
    public ResponseEntity<?> saveCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        CustomerDTO result = (CustomerDTO) customerServiceImpl.save(customerDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{tckn}")
    public ResponseEntity<?> updateCustomer(@RequestBody @Valid CustomerDTO customerDTO, @PathVariable String tckn) {
        CustomerDTO result = customerServiceImpl.update(customerDTO, tckn);
        return ResponseEntity.ok(result);
    }

}
