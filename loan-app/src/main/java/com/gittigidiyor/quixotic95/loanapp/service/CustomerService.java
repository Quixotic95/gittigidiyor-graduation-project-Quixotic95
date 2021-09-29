package com.gittigidiyor.quixotic95.loanapp.service;

import com.gittigidiyor.quixotic95.loanapp.dto.CustomerDTO;
import com.gittigidiyor.quixotic95.loanapp.service.generic.GenericService;

import java.util.List;
import java.util.UUID;

public interface CustomerService extends GenericService<CustomerDTO> {

    List<CustomerDTO> findAllCustomers();

    CustomerDTO findCustomerById(UUID customerId);

    CustomerDTO findCustomerByTckn(String tckn);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    CustomerDTO deleteCustomerByTckn(String tckn);

    CustomerDTO deleteCustomerById(UUID customerId);

}