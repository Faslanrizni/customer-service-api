package com.devstack.customerserviceapi.api;

import com.devstack.customerserviceapi.dto.CustomerDto;
import com.devstack.customerserviceapi.util.StandardResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/customers")
public class CustomerController {
    public ResponseEntity<StandardResponseEntity> createCustomer(@RequestBody CustomerDto customerDto){
        return new ResponseEntity<>(
                new StandardResponseEntity(200,"message",null),
                HttpStatus.OK
        );

    }
}
