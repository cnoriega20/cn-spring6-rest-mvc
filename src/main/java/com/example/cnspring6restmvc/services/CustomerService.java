package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer getCustomerById(UUID id);
    List<Customer> listCustomers();
}
