package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    private Map<UUID, Customer> customerMap;

    public CustomerServiceImpl() {
        this.customerMap = new HashMap<>();
        Customer customer1 = Customer.builder()
                .id(UUID.fromString("efa4211e-8060-4941-aa52-95986debbf9b"))
                .customerName("DY")
                .version("1.0")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        Customer customer2 = Customer.builder()
                .id(UUID.fromString("3b05b574-fb6e-4783-864e-dd956bc773d4"))
                .customerName("Feid")
                .version("1.0")
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
        customerMap.put(customer1.getId(), customer1);
        customerMap.put(customer2.getId(), customer2);

    }

    @Override
    public Customer getCustomerById(UUID id) {
        log.info("Get Customer by Id - In getCustomerById with id {}",id.toString());
        return customerMap.get(id);
    }

    @Override
    public List<Customer> listCustomers() {
        log.info("Inside CustomerService - listCustomers() ..");
        return new ArrayList<>(customerMap.values());
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        Customer savedCustomer = Customer.builder()
                .id(UUID.randomUUID())
                .createdDate(LocalDateTime.now())
                .version(customer.getVersion())
                .customerName(customer.getCustomerName())
                .build();
        customerMap.put(savedCustomer.getId(), savedCustomer);
        return savedCustomer;
    }
}
