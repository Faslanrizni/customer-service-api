package com.devstack.customerserviceapi.service.impl;

import com.devstack.customerserviceapi.config.WebClientConfig;
import com.devstack.customerserviceapi.dto.CustomerDto;
import com.devstack.customerserviceapi.dto.OrderDto;
import com.devstack.customerserviceapi.dto.ResponseCustomerDto;
import com.devstack.customerserviceapi.dto.ResponseOrderDto;
import com.devstack.customerserviceapi.entity.Customer;
import com.devstack.customerserviceapi.feigns.OrderFeignClient;
import com.devstack.customerserviceapi.repo.CustomerRepo;
import com.devstack.customerserviceapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;

    @Autowired
    private WebClient webClient;

    @Autowired
    private OrderFeignClient orderFeignClient;

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
    public ResponseCustomerDto findCustomerById(Long id) {
        Optional<Customer> selectedCustomer = customerRepo.findById(id);
        if (selectedCustomer.isEmpty()){
            throw new RuntimeException("Not found");
        }
//        ResponseOrderDto orders = findOrders(selectedCustomer.get().getId());
          ResponseOrderDto orders = orderFeignClient.findOrdersByCustomer(id);
        CustomerDto customerDto = new CustomerDto(
                selectedCustomer.get().getId(),
                selectedCustomer.get().getName(),
                selectedCustomer.get().getAddress(),
                selectedCustomer.get().getSalary()
        );
        return new ResponseCustomerDto(customerDto,orders);
    }
    private ResponseOrderDto findOrders(Long id){
        Mono<ResponseOrderDto> orderDtoMono = webClient.get().uri("/get-by-customer-id/" + id)
                .retrieve().bodyToMono(ResponseOrderDto.class);

        return orderDtoMono.block();
    }

}
