package com.gittigidiyor.quxiotic95.creditapp.service.implementation;

import com.gittigidiyor.quxiotic95.creditapp.dto.CustomerDTO;
import com.gittigidiyor.quxiotic95.creditapp.dto.generic.GenericDTO;
import com.gittigidiyor.quxiotic95.creditapp.entity.Customer;
import com.gittigidiyor.quxiotic95.creditapp.mapper.CustomerMapper;
import com.gittigidiyor.quxiotic95.creditapp.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {

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

    @Override
    public GenericDTO<CustomerDTO> save(CustomerDTO customerDTO) {
        Customer result = CustomerMapper.INSTANCE.toCustomerEntity(customerDTO);
        return CustomerMapper.INSTANCE.toCustomerDto(result);
    }

}
