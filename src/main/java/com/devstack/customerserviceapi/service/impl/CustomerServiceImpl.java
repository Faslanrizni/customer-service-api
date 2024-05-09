package com.devstack.customerserviceapi.service.impl;

import com.devstack.customerserviceapi.dto.CustomerDto;
import com.devstack.customerserviceapi.entity.Customer;
import com.devstack.customerserviceapi.repo.CustomerRepo;
import com.devstack.customerserviceapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Autowired
    public CustomerServiceImpl(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }


    @Override
    public void createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer(
                customerDto.getId(),customerDto.getName(),customerDto.getAddress(),customerDto.getSalary()
        );
        customerRepo.save(customer);
    }

}
