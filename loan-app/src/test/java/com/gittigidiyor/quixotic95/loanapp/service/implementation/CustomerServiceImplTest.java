package com.gittigidiyor.quixotic95.loanapp.service.implementation;

import com.gittigidiyor.quixotic95.loanapp.dto.CustomerDTO;
import com.gittigidiyor.quixotic95.loanapp.entity.Customer;
import com.gittigidiyor.quixotic95.loanapp.exception.CustomerAlreadyExistsWithTCKNException;
import com.gittigidiyor.quixotic95.loanapp.mapper.CustomerMapper;
import com.gittigidiyor.quixotic95.loanapp.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapper customerMapper;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void should_return_CustomerDTO_when_saveCustomer_runs() {

        Customer customer = new Customer();
        CustomerDTO customerDTO = new CustomerDTO();

        when(customerRepository.save(any())).thenReturn(customer);

        when(customerMapper.toCustomerDto(customer)).thenReturn(customerDTO);

        CustomerDTO expected = customerMapper.toCustomerDto(customer);

        CustomerDTO actual = customerService.saveCustomer(customerDTO);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(expected, actual),
                () -> verify(customerMapper).toCustomerDto(customer)
        );
    }

    @Test
    void should_throw_CustomerAlreadyExistsWithTCKNException_when_saveCourse_runs_if_a_Customer_already_exists_with_same_TCKN() {

        Customer customer = new Customer();
        customer.setTckn("23570297588");

        when(customerRepository.findCustomerByTckn(anyString())).thenReturn(Optional.of(customer));

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setTckn("23570297588");

        when(customerMapper.toCustomerDto(customer)).thenReturn(customerDTO);

        assertThrows(CustomerAlreadyExistsWithTCKNException.class, () -> customerService.saveCustomer(customerMapper.toCustomerDto(customer)));
    }

}