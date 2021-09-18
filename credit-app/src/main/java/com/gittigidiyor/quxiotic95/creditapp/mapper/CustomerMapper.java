package com.gittigidiyor.quxiotic95.creditapp.mapper;

import com.gittigidiyor.quxiotic95.creditapp.dto.CustomerDTO;
import com.gittigidiyor.quxiotic95.creditapp.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toCustomerEntity(CustomerDTO customerDTO);

    CustomerDTO toCustomerDto(Customer customer);

}
