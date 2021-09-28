package com.gittigidiyor.quixotic95.loanapp.controller;

import com.gittigidiyor.quixotic95.loanapp.dto.CustomerDTO;
import com.gittigidiyor.quixotic95.loanapp.entity.Customer;
import com.gittigidiyor.quixotic95.loanapp.service.CustomerService;
import com.gittigidiyor.quixotic95.loanapp.validation.group.OrderedChecks;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customers")
@Validated
@RequiredArgsConstructor
public class CustomerRestController {

    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> findAllCustomers() {

        List<CustomerDTO> result = customerService.findAllCustomers();

        return ResponseEntity.ok(result);

    }

    @GetMapping("id/{customerId}")
    public ResponseEntity<?> findCustomerById(@PathVariable(name = "customerId") UUID customerId) {

        CustomerDTO result = customerService.findCustomerById(customerId);

        return ResponseEntity.ok(result);

    }

    @GetMapping("tckn/{tckn}")
    ResponseEntity<?> findCustomerByTckn(@PathVariable(name = "tckn") String tckn) {

        CustomerDTO result = customerService.findCustomerByTckn(tckn);

        return ResponseEntity.ok(result);

    }

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

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable(name = "id") UUID customerId) {

        CustomerDTO result = customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok(result);

    }

    @DeleteMapping("tckn/{tckn}")
    @ApiOperation(value = "Delete Customer", notes = "This endpoint deletes the customer with given TCKN from database.")
    public ResponseEntity<?> deleteCustomerByTckn(@PathVariable(name = "tckn")
                                                  @NotBlank
                                                  @Pattern(regexp = "^[1-9][0-9]{10}$", message = "TCKN can not start with 0 and must contain 11 numbers!")
                                                          String tckn) {

        CustomerDTO result = customerService.deleteCustomerByTckn(tckn);
        return ResponseEntity.ok(result);

    }


}
