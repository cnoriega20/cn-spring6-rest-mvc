package com.example.cnspring6restmvc.mappers;

import com.example.cnspring6restmvc.entities.Customer;
import com.example.cnspring6restmvc.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    Customer customerDToToCustomer(CustomerDTO customerDTO);
    CustomerDTO customerToCustomerDTO(Customer customer);
}
