package com.example.cnspring6restmvc.controller;

import com.example.cnspring6restmvc.model.Customer;
import com.example.cnspring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping()
    public List<Customer> listCustomers(){
        return customerService.listCustomers();
    }

    @GetMapping("{id}")
    public Customer getCustomerById(@PathVariable("id") UUID id) {
        log.info("Get Customer by Id - In Customer Controller");
        return customerService.getCustomerById(id);
    }
}
