package com.devstack.customerserviceapi.service.impl;

import com.devstack.customerserviceapi.dto.CustomerDto;
import com.devstack.customerserviceapi.entity.Customer;
import com.devstack.customerserviceapi.repo.CustomerRepo;
import com.devstack.customerserviceapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public CustomerDto findCustomerById(Long id) {
        Optional<Customer> selectedCustomer = customerRepo.findById(id);
        if (selectedCustomer.isEmpty()){
            throw new RuntimeException("Not found");
        }
        return new CustomerDto(
                selectedCustomer.get().getId(),
                selectedCustomer.get().getName(),
                selectedCustomer.get().getAddress(),
                selectedCustomer.get().getSalary()

        );

    }

}
