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

        Optional<Customer> existingCustomer = customerRepository.findCustomerByTcknAndIdIsNot(customerDTO.getTckn(), UUID.randomUUID());
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsWithTCKNException("This TCKN already belongs to another customer in the system.");
        }

        existingCustomer = customerRepository.findCustomerByPhoneNumberAndIdIsNot(customerDTO.getPhoneNumber(), UUID.randomUUID());
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsWithPhoneNumberException("This phone number already belongs to another customer in the system.");
        }

        Customer result = CustomerMapper.INSTANCE.toCustomerEntity(customerDTO);
        return CustomerMapper.INSTANCE.toCustomerDto(customerRepository.save(result));
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO, String tckn) {
        Customer result = CustomerMapper.INSTANCE.toCustomerEntity(customerDTO);
        result.setFirstName("Updated");
        result.setLastName("Customer");
        return CustomerMapper.INSTANCE.toCustomerDto(result);
    }

    @Override
    public CustomerDTO delete(String tckn) {
        Customer result = new Customer();
        result.setFirstName("Customer with tckn");
        result.setLastName(tckn + "will be deleted");
        result.setMonthlyIncome(200);
        result.setPhoneNumber(";)");
        return CustomerMapper.INSTANCE.toCustomerDto(result);
    }

}
