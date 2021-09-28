package com.gittigidiyor.quixotic95.loanapp.mapper;

import com.gittigidiyor.quixotic95.loanapp.dto.CustomerDTO;
import com.gittigidiyor.quixotic95.loanapp.dto.CustomerUpdateDTO;
import com.gittigidiyor.quixotic95.loanapp.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toCustomerEntity(CustomerDTO customerDTO);

    CustomerDTO toCustomerDto(Customer customer);

    Customer fromCustomerUpdateDTOtoEntity(CustomerUpdateDTO customerUpdateDTO);

}
