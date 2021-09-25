package com.gittigidiyor.quxiotic95.creditapp.service;

import com.gittigidiyor.quxiotic95.creditapp.dto.CustomerDTO;
import com.gittigidiyor.quxiotic95.creditapp.service.generic.GenericService;

public interface CustomerService extends GenericService<CustomerDTO> {

    CustomerDTO findCustomerByTckn(String tckn);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    CustomerDTO deleteCustomer(String tckn);

    CustomerDTO saveCustomer(CustomerDTO customerDTO);

}
