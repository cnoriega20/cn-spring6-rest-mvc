package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.model.Customer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Optional<Customer> getCustomerById(UUID id);
    List<Customer> listCustomers();

    Customer saveCustomer(Customer beer);

    void updateCustomerById(UUID customerId,Customer customer);

    void deleteById(UUID customerId);
}
