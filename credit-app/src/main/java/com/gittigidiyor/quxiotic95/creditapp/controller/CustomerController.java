package com.gittigidiyor.quxiotic95.creditapp.controller;

import com.gittigidiyor.quxiotic95.creditapp.dto.CustomerDTO;
import com.gittigidiyor.quxiotic95.creditapp.service.implementation.CustomerServiceImpl;
import com.gittigidiyor.quxiotic95.creditapp.validation.group.OrderedChecks;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("customer")
@Validated
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    @PostMapping
    @ApiOperation(value = "Create Customer", notes = "This endpoint saves customer to database.")
    public ResponseEntity<?> saveCustomer(@RequestBody @Validated(OrderedChecks.class) CustomerDTO customerDTO) {
        CustomerDTO result = (CustomerDTO) customerServiceImpl.save(customerDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping
    @ApiOperation(value = "Update Customer", notes = "This endpoint updates fields of existing customer with given TCKN.")
    public ResponseEntity<?> updateCustomer(@RequestBody @Validated(OrderedChecks.class) CustomerDTO customerDTO) {
        CustomerDTO result = customerServiceImpl.update(customerDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("{tckn}")
    @ApiOperation(value = "Delete Customer", notes = "This endpoint deletes the customer with given TCKN from database.")
    public ResponseEntity<?> deleteCustomer(@PathVariable(name = "tckn")
                                            @NotBlank
                                            @Pattern(regexp = "^[1-9][0-9]{10}$", message = "TCKN can not start with 0 and must contain 11 numbers!")
                                                    String tckn) {
        CustomerDTO result = customerServiceImpl.delete(tckn);
        return ResponseEntity.ok(result);
    }

}
