package com.gittigidiyor.quxiotic95.creditapp.repository;

import com.gittigidiyor.quxiotic95.creditapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findCustomerByTcknAndIdIsNot(String tckn, UUID id);

    Optional<Customer> findCustomerByPhoneNumberAndIdIsNot(String phoneNumber, UUID id);

}
