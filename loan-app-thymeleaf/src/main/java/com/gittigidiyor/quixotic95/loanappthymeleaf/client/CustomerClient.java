package com.gittigidiyor.quixotic95.loanappthymeleaf.client;

import com.gittigidiyor.quixotic95.loanappthymeleaf.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(value = "customers", url = "localhost:8080/api/customers")
public interface CustomerClient {

    @GetMapping
    ResponseEntity<List<Customer>> findAllCustomers();

    @GetMapping("id/{customerId}")
    ResponseEntity<Customer> findCustomerById(@PathVariable(name = "customerId") UUID customerId);

    @GetMapping("tckn/{tckn}")
    ResponseEntity<Customer> findCustomerByTckn(@PathVariable(name = "tckn") String tckn);

    @PostMapping
    ResponseEntity<Customer> saveCustomer(Customer customer);

    @PutMapping
    ResponseEntity<Customer> updateCustomer(Customer customer);

    @DeleteMapping("id/{id}")
     ResponseEntity<?> deleteCustomerById(@PathVariable(name = "id") UUID customerId);

}
