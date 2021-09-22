package com.gittigidiyor.quxiotic95.creditapp.service;

import com.gittigidiyor.quxiotic95.creditapp.dto.CustomerDTO;
import com.gittigidiyor.quxiotic95.creditapp.service.generic.GenericService;

public interface CustomerService extends GenericService<CustomerDTO> {

    CustomerDTO update(CustomerDTO customerDTO);
    CustomerDTO delete(String tckn);

}
