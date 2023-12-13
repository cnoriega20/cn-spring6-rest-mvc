package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.model.CustomerDTO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    Optional<CustomerDTO> getCustomerById(UUID id);
    List<CustomerDTO> listCustomers();

    CustomerDTO saveCustomer(CustomerDTO beer);

    void updateCustomerById(UUID customerId, CustomerDTO customer);

    void deleteById(UUID customerId);
}
