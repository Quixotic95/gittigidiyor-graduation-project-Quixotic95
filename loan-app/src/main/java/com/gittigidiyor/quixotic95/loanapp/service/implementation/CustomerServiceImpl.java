package com.gittigidiyor.quixotic95.loanapp.service.implementation;

import com.gittigidiyor.quixotic95.loanapp.dto.CustomerDTO;
import com.gittigidiyor.quixotic95.loanapp.entity.Customer;
import com.gittigidiyor.quixotic95.loanapp.exception.CustomerAlreadyExistsWithPhoneNumberException;
import com.gittigidiyor.quixotic95.loanapp.exception.CustomerAlreadyExistsWithTCKNException;
import com.gittigidiyor.quixotic95.loanapp.mapper.CustomerMapper;
import com.gittigidiyor.quixotic95.loanapp.repository.CustomerRepository;
import com.gittigidiyor.quixotic95.loanapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

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

    @Override
    public CustomerDTO findCustomerById(UUID customerId) {
        return CustomerMapper.INSTANCE.toCustomerDto(customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("An error occurred while trying to get Customer!")));
    }

    @Override
    public CustomerDTO findCustomerByTckn(String tckn) {
        return CustomerMapper.INSTANCE.toCustomerDto(customerRepository.findCustomerByTckn(tckn)
                .orElseThrow(() -> new EntityNotFoundException("Customer with TCKN: " + tckn + " couldn't found!")));
    }

    @Override
    public List<CustomerDTO> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper.INSTANCE::toCustomerDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {

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
    public CustomerDTO deleteCustomerByTckn(String tckn) {

        Customer existingCustomer = customerRepository.findCustomerByTckn(tckn)
                .orElseThrow(() -> new EntityNotFoundException("Customer with TCKN: " + tckn + " couldn't found!"));

        CustomerDTO result = CustomerMapper.INSTANCE.toCustomerDto(existingCustomer);
        customerRepository.delete(existingCustomer);

        return result;

    }

    @Transactional
    @Override
    public CustomerDTO deleteCustomerById(UUID customerId) {

        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("An error occurred while trying to get Customer!"));

        CustomerDTO result = CustomerMapper.INSTANCE.toCustomerDto(existingCustomer);
        customerRepository.delete(existingCustomer);

        return result;

    }

}
