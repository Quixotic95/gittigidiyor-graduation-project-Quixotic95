package com.gittigidiyor.quxiotic95.creditapp.service.implementation;

import com.gittigidiyor.quxiotic95.creditapp.dto.CustomerDTO;
import com.gittigidiyor.quxiotic95.creditapp.dto.generic.GenericDTO;
import com.gittigidiyor.quxiotic95.creditapp.entity.Customer;
import com.gittigidiyor.quxiotic95.creditapp.exception.CustomerAlreadyExistsWithPhoneNumberException;
import com.gittigidiyor.quxiotic95.creditapp.exception.CustomerAlreadyExistsWithTCKNException;
import com.gittigidiyor.quxiotic95.creditapp.mapper.CustomerMapper;
import com.gittigidiyor.quxiotic95.creditapp.repository.CustomerRepository;
import com.gittigidiyor.quxiotic95.creditapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    @Override
    public GenericDTO<CustomerDTO> save(CustomerDTO customerDTO) {

        Optional<Customer> optionalExistingCustomer = customerRepository.findCustomerByTckn(customerDTO.getTckn());
        if (optionalExistingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsWithTCKNException("This TCKN already belongs to another customer in the system.");
        }

        optionalExistingCustomer = customerRepository.findCustomerByPhoneNumberAndIdIsNot(customerDTO.getPhoneNumber(), UUID.randomUUID());
        if (optionalExistingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsWithPhoneNumberException("This phone number already belongs to another customer in the system.");
        }

        Customer result = CustomerMapper.INSTANCE.toCustomerEntity(customerDTO);
        return CustomerMapper.INSTANCE.toCustomerDto(customerRepository.save(result));

    }

    @Transactional
    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {

        Customer existingCustomer = customerRepository.findCustomerByTckn(customerDTO.getTckn())
                .orElseThrow(() -> new EntityNotFoundException("Customer with TCKN: " + customerDTO.getTckn() + " couldn't found!"));

        if (customerRepository.findCustomerByPhoneNumberAndIdIsNot(customerDTO.getPhoneNumber(), existingCustomer.getId()).isPresent()) {
            throw new CustomerAlreadyExistsWithPhoneNumberException("This phone number already belongs to another customer in the system.");
        }

        Customer result = CustomerMapper.INSTANCE.toCustomerEntity(customerDTO);
        result.setId(existingCustomer.getId());

        return CustomerMapper.INSTANCE.toCustomerDto(customerRepository.save(result));

    }

    @Transactional
    @Override
    public CustomerDTO delete(String tckn) {

        Customer existingCustomer = customerRepository.findCustomerByTckn(tckn)
                .orElseThrow(() -> new EntityNotFoundException("Customer with TCKN: " + tckn + " couldn't found!"));

        CustomerDTO result = CustomerMapper.INSTANCE.toCustomerDto(existingCustomer);
        customerRepository.delete(existingCustomer);

        return result;

    }

}
