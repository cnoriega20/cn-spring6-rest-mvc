package com.example.cnspring6restmvc.controller;

import com.example.cnspring6restmvc.model.Beer;
import com.example.cnspring6restmvc.model.Customer;
import com.example.cnspring6restmvc.services.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
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
    @PostMapping
    public ResponseEntity handlePost(@RequestBody Customer beer) {
        Customer savedCustomer = customerService.saveCustomer(beer);

        //Response headers
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/customer/" + savedCustomer.getId().toString());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("{customerId}")
    public ResponseEntity updateCustomerById(@PathVariable("customerId") UUID customerId,
                                             @RequestBody Customer customer) {
        customerService.updateCustomerById(customerId, customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
