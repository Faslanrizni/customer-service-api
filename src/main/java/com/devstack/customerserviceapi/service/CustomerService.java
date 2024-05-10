package com.devstack.customerserviceapi.service;

import com.devstack.customerserviceapi.dto.CustomerDto;
import com.devstack.customerserviceapi.dto.ResponseCustomerDto;
import com.devstack.customerserviceapi.repo.CustomerRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface CustomerService {

    public void createCustomer(CustomerDto customerDto);
    public ResponseCustomerDto findCustomerById(Long id);
}
