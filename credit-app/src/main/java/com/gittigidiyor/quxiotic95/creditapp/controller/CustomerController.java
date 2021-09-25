package com.gittigidiyor.quxiotic95.creditapp.controller;

import com.gittigidiyor.quxiotic95.creditapp.dto.CustomerDTO;
import com.gittigidiyor.quxiotic95.creditapp.service.CustomerService;
import com.gittigidiyor.quxiotic95.creditapp.validation.group.OrderedChecks;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("customers")
@Validated
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @ApiOperation(value = "Create Customer", notes = "This endpoint saves customer to database.")
    public ResponseEntity<?> saveCustomer(@RequestBody @Validated(OrderedChecks.class) CustomerDTO customerDTO) {
        CustomerDTO result = customerService.saveCustomer(customerDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    @ApiOperation(value = "Update Customer", notes = "This endpoint updates fields of existing customer with given TCKN.")
    public ResponseEntity<?> updateCustomer(@RequestBody @Validated(OrderedChecks.class) CustomerDTO customerDTO) {
        CustomerDTO result = customerService.updateCustomer(customerDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{tckn}")
    @ApiOperation(value = "Delete Customer", notes = "This endpoint deletes the customer with given TCKN from database.")
    public ResponseEntity<?> deleteCustomerByTckn(@PathVariable(name = "tckn")
                                                  @NotBlank
                                                  @Pattern(regexp = "^[1-9][0-9]{10}$", message = "TCKN can not start with 0 and must contain 11 numbers!")
                                                          String tckn) {
        CustomerDTO result = customerService.deleteCustomer(tckn);
        return ResponseEntity.ok(result);
    }

}
