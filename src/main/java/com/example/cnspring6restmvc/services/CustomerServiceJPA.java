package com.example.cnspring6restmvc.services;

import com.example.cnspring6restmvc.mappers.CustomerMapper;
import com.example.cnspring6restmvc.model.CustomerDTO;
import com.example.cnspring6restmvc.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Primary
public class CustomerServiceJPA implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public Optional<CustomerDTO> getCustomerById(UUID id) {
        return Optional.ofNullable(customerMapper.customerToCustomerDTO(
                customerRepository.findById(id).orElse(null)));
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .toList();
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customer) {
        return customerMapper.customerToCustomerDTO(customerRepository.save(customerMapper.customerDToToCustomer(customer)));
    }

    @Override
    public void updateCustomerById(UUID customerId, CustomerDTO customer) {

    }

    @Override
    public void deleteById(UUID customerId) {

    }
}
